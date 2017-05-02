package com.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author Ekaterina Pyataeva on 01.05.2017.
 */


@Service("mailService")
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaMailSender.class);

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Async
    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    @Async
    public void sendPreConfiguredMail(String to, SimpleMailMessage configuredMessage) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(configuredMessage);
        mailMessage.setTo(to);
        javaMailSender.send(mailMessage);
    }


}
