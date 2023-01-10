package com.tripmate.domain.common.service;

import com.tripmate.domain.common.dto.MailDTO;

import javax.mail.MessagingException;

public interface MailService {
    void sendMail(MailDTO mail) throws MessagingException;
}