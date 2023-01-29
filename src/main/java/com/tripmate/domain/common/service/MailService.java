package com.tripmate.domain.common.service;

import com.tripmate.domain.common.dto.MailDTO;
import com.tripmate.domain.members.dto.MemberMailDTO;

import javax.mail.MessagingException;

public interface MailService {
    void sendMail(MailDTO mailDTO) throws MessagingException;
    void sendCertificationMail(MemberMailDTO memberMailDTO) throws MessagingException;
    void sendPasswordMail(MemberMailDTO memberMailDTO) throws MessagingException;
}