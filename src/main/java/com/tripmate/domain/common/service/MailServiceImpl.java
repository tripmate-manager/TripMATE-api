package com.tripmate.domain.common.service;

import com.tripmate.domain.common.ConstCode;
import com.tripmate.domain.common.dto.MailDTO;
import com.tripmate.domain.member.dao.MemberDAO;
import com.tripmate.domain.member.dto.MemberMailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService {
    private final MailHandler mailHandler;
    private final MemberDAO memberDAO;

    @Autowired
    public MailServiceImpl(MailHandler mailHandler, MemberDAO memberDAO) {
        this.mailHandler = mailHandler;
        this.memberDAO = memberDAO;
    }

    public void sendSignUpMail(MailDTO mailDTO) throws MessagingException {
        String key = TempKey(30, false);

        String mailContents = "<h3>TripMATE 회원가입 인증 메일</h3>" +
                "<h4>아래 링크를 클릭하여 인증을 완료해주세요.</h4><br>" +
                "<a href='http://localhost:8080/members/signUp/emailConfirm?email=" + mailDTO.getEmail() +
                "&key=" + key +
                "' target='_blenk'>이메일 인증하기</a>";

        mailHandler.setTo(mailDTO.getEmail());
        mailHandler.setSubject("TripMATE 회원가입 인증 메일입니다.");
        mailHandler.setText(mailContents, true);
        mailHandler.send();

        MemberMailDTO memberMailDTO= MemberMailDTO.builder()
                .email(mailDTO.getEmail())
                .key(key)
                .mailTypeCode(ConstCode.EMAIL_TYPE_CODE_JOIN)
                .build();

        memberDAO.insertEmailInfo(memberMailDTO);
    }


    public String TempKey(int size, boolean lowerCheck) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        int num = 0;
        do {
            num = random.nextInt(75) + 48;
            if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
                stringBuffer.append((char) num);
            } else {
                continue;
            }
        } while (stringBuffer.length() < size);
        if (lowerCheck) {
            return stringBuffer.toString().toLowerCase();
        }
        return stringBuffer.toString();

    }
}