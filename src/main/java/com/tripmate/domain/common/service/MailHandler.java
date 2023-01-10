package com.tripmate.domain.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
class MailHandler {
    private final JavaMailSender sender;
    private final MimeMessage mimeMessage;
    private final MimeMessageHelper mimeMessageHelper;

    @Autowired
    public MailHandler(JavaMailSender javaMailSender) throws MessagingException {
        this.sender = javaMailSender;
        this.mimeMessage = javaMailSender.createMimeMessage();
        this.mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
    }

    public MailHandler(JavaMailSender sender, MimeMessage mimeMessage, MimeMessageHelper mimeMessageHelper) {
        this.sender = sender;
        this.mimeMessage = mimeMessage;
        this.mimeMessageHelper = mimeMessageHelper;
    }

    public void setFrom(String fromAddress) throws MessagingException {
        mimeMessageHelper.setFrom(fromAddress);
    }

    public void setTo(String email) throws MessagingException {
        mimeMessageHelper.setTo(email);
    }

    public void setSubject(String subject) throws MessagingException {
        mimeMessageHelper.setSubject(subject);
    }

    public void setText(String text, boolean useHtml) throws MessagingException {
        mimeMessageHelper.setText(text, useHtml);
    }

    public void setAttach(String fileName, String path) throws MessagingException, IOException {
        File file = new ClassPathResource(path).getFile();
        FileSystemResource fsr = new FileSystemResource(file);

        mimeMessageHelper.addAttachment(fileName, fsr);
    }

    public void setInline(String fileName, String path) throws MessagingException, IOException {
        File file = new ClassPathResource(path).getFile();
        FileSystemResource fileSystemResource = new FileSystemResource(file);

        mimeMessageHelper.addInline(fileName, fileSystemResource);
    }

    public void send() {
        sender.send(mimeMessage);
    }
}
