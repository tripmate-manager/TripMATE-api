package com.tripmate.domain.common.service;

import com.tripmate.domain.common.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class MailServiceImpl implements MailService {
    private final MailHandler mailHandler;

    @Autowired
    public MailServiceImpl(MailHandler mailHandler) {
        this.mailHandler = mailHandler;
    }

    public void sendMail(MailDTO mail) throws MessagingException {
        mailHandler.setTo(mail.getTo());
        mailHandler.setSubject(mail.getSubject());
        mailHandler.setText(mail.getMessage(), true);

        mailHandler.send();
    }
}