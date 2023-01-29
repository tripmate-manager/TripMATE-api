package com.tripmate.domain.common.service;

import com.tripmate.common.exception.NoResultException;
import com.tripmate.domain.common.Const;
import com.tripmate.domain.common.Encrypt;
import com.tripmate.domain.common.dto.MailDTO;
import com.tripmate.domain.members.dao.MemberDAO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.members.dto.MemberMailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

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

    public void sendCertificationMail(MemberMailDTO memberMailDTO) throws MessagingException {
        Encrypt encrypt = new Encrypt();

        String key = encrypt.getEncrypt(encrypt.getSalt(), Const.SERVICE_NAME);

        String mailContents = "<h3>TripMATE 회원가입 인증 메일</h3>" +
                "<h4>아래 링크를 클릭하여 인증을 완료해주세요.</h4><br>" +
                "<a href='" + Const.JOIN_EMAIL_URL +
                "?memberId=" + memberMailDTO.getMemberId() +
                "&key=" + key +
                "&mailTypeCode=" + memberMailDTO.getMailTypeCode() +
                "' target='_blenk'>이메일 인증하기</a>";

        mailHandler.setTo(memberMailDTO.getTo());
        mailHandler.setSubject("TripMATE 회원가입 인증 메일입니다.");
        mailHandler.setText(mailContents, true);
        mailHandler.send();

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
    }

    public void sendPasswordMail(MemberMailDTO memberMailDTO) throws MessagingException {
        Integer memberNo = memberDAO.selectFindPassword(memberMailDTO);

        if (memberNo != null) {
            //TODO: 임시비밀번호 생성 메서드 추가
            String password = "";

            String mailContents = "<h3>TripMATE 임시 비밀번호 발급 메일</h3>" +
                    "<h4>아래 임시 비밀번호를 사용해 로그인 해주세요.</h4><br>" +
                    "<h5>" + password + "</h5><br>" +
                    "<h5>로그인 후 비밀번호를 변경하세요.</h5>";

            mailHandler.setTo(memberMailDTO.getTo());
            mailHandler.setSubject("TripMATE 회원가입 인증 메일입니다.");
            mailHandler.setText(mailContents, true);
            mailHandler.send();

            MemberDTO memberDTO = MemberDTO.builder()
                    .memberNo(memberNo)
                    .memberPassword(password)
                    .build();

            memberDAO.updateMemberPassword(memberDTO);
        } else {
            throw new NoResultException("존재하지 않는 회원 정보입니다.");
        }
    }
}