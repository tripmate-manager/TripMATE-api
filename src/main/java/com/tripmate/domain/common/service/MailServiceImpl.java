package com.tripmate.domain.common.service;

import com.tripmate.common.exception.NoResultException;
import com.tripmate.domain.common.Const;
import com.tripmate.domain.common.Encrypt;
import com.tripmate.domain.common.dto.MailDTO;
import com.tripmate.domain.members.dao.MemberDAO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.members.dto.MemberMailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
@Service
public class MailServiceImpl implements MailService {
    private final MailHandler mailHandler;
    private final MemberDAO memberDAO;

    @Autowired
    public MailServiceImpl(MailHandler mailHandler, MemberDAO memberDAO) {
        this.mailHandler = mailHandler;
        this.memberDAO = memberDAO;
    }

    @Override
    public void sendMail(MailDTO mailDTO) throws MessagingException {
        mailHandler.setTo(mailDTO.getTo());
        mailHandler.setSubject(mailDTO.getSubject());
        mailHandler.setText(mailDTO.getMessage(), true);
        mailHandler.send();
    }

    public boolean sendCertificationMail(MemberMailDTO memberMailDTO) throws MessagingException {
        Encrypt encrypt = new Encrypt();
        String key = encrypt.getEncrypt(encrypt.getSalt(), Const.SERVICE_NAME);

        String mailContents;
        try {
            mailContents = readMailTemplate("certificationMail.html");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        String certificationUrl = Const.JOIN_EMAIL_URL +
                "?memberId=" + memberMailDTO.getMemberId() +
                "&key=" + key +
                "&mailTypeCode=" + memberMailDTO.getMailTypeCode();
        mailContents = mailContents.replace("%certificationUrl%", certificationUrl);

        try {
            mailHandler.setTo(memberMailDTO.getTo());
            mailHandler.setSubject("TripMATE 회원가입 인증 메일입니다.");
            mailHandler.setText(mailContents, true);
            mailHandler.send();
        } catch (MessagingException e) {
            throw new MessagingException("메일 전송 중 오류가 발생하였습니다.");
        }

        MemberMailDTO mailInfoDTO = MemberMailDTO.builder()
                .memberId(memberMailDTO.getMemberId())
                .to(memberMailDTO.getTo())
                .key(key)
                .mailTypeCode(memberMailDTO.getMailTypeCode())
                .build();

        if (memberDAO.selectAuthEmailCnt(mailInfoDTO) > 0) {
            memberDAO.updateEmailInfo(mailInfoDTO);
        } else {
            memberDAO.insertEmailInfo(mailInfoDTO);
        }

        return true;
    }

    public boolean sendPasswordMail(MemberMailDTO memberMailDTO) throws MessagingException {
        int memberNo = memberDAO.selectFindPasswordMbrNo(memberMailDTO);

        if (memberNo > 0) {
            Encrypt encrypt = new Encrypt();
            String encryptString = encrypt.getEncrypt(encrypt.getSalt(), Const.SERVICE_NAME);

            String[] specialSymbols = {".", "*", "!", "?", "$"};
            double random = Math.random();
            int index = (int) Math.round(random * (specialSymbols.length - 1));

            String password = encryptString.substring(0, 8) + specialSymbols[index];

            String mailContents;
            try {
                mailContents = readMailTemplate("temporaryPasswordMail.html");
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                return false;
            }
            mailContents = mailContents.replace("%password%", password);

            try {
                mailHandler.setTo(memberMailDTO.getTo());
                mailHandler.setSubject("TripMATE 임시 비밀번호 발급 메일입니다.");
                mailHandler.setText(mailContents, true);
                mailHandler.send();
            } catch (MessagingException e) {
                throw new MessagingException("메일 전송 중 오류가 발생하였습니다.");
            }

            MemberDTO memberDTO = MemberDTO.builder()
                    .memberNo(memberNo)
                    .memberPassword(password)
                    .build();

            memberDAO.updateMemberPassword(memberDTO);
        } else {
            throw new NoResultException("존재하지 않는 회원 정보입니다.");
        }

        return true;
    }

    private String readMailTemplate(String fileName) throws IOException {
        String mailContents = "";
        String path = System.getProperty("user.dir") + Const.MAIL_TEMPLATES_PATH + fileName;

        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            mailContents += line;
        }

        return mailContents;
    }
}