package com.tripmate.domain.common.service;

import com.tripmate.domain.common.Const;
import com.tripmate.domain.common.ConstCode;
import com.tripmate.domain.common.Encrypt;
import com.tripmate.domain.common.dto.MailDTO;
import com.tripmate.domain.members.dao.MemberDAO;
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

    public void sendSignUpMail(MailDTO mailDTO) throws MessagingException {
        Encrypt encrypt = new Encrypt();

        String key = encrypt.getEncrypt(encrypt.getSalt(), Const.SERVICE_NAME);

        String mailContents = "<h3>TripMATE 회원가입 인증 메일</h3>" +
                "<h4>아래 링크를 클릭하여 인증을 완료해주세요.</h4><br>" +
                "<a href='" + Const.JOIN_EMAIL_URL +
                "?email=" + mailDTO.getTo() +
                "&key=" + key +
                "' target='_blenk'>이메일 인증하기</a>";

        mailHandler.setTo(mailDTO.getTo());
        mailHandler.setSubject("TripMATE 회원가입 인증 메일입니다.");
        mailHandler.setText(mailContents, true);
        mailHandler.send();

        MemberMailDTO memberMailDTO= MemberMailDTO.builder()
                .email(mailDTO.getTo())
                .key(key)
                .mailTypeCode(ConstCode.EMAIL_TYPE_CODE_JOIN)
                .build();

        memberDAO.insertEmailInfo(memberMailDTO);
    }
}