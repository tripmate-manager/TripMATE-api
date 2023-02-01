package com.tripmate.domain.common.service;

import com.tripmate.domain.common.dto.MailDTO;
import com.tripmate.domain.members.dto.MemberMailDTO;

import javax.mail.MessagingException;

public interface MailService {
    void sendMail(MailDTO mailDTO) throws MessagingException;
    boolean sendCertificationMail(MemberMailDTO memberMailDTO) throws MessagingException;
    boolean sendPasswordMail(MemberMailDTO memberMailDTO) throws MessagingException;
}