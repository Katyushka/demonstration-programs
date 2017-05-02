package com.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author Ekaterina Pyataeva on 01.05.2017.
 */


@Configuration
public class MailConfiguration {

    @Bean
    public JavaMailSenderImpl emailSender(@Value("${mail.protocol}")
                                                  String protocol,
                                          @Value("${mail.host}")
                                                  String host,
                                          @Value("${mail.port}")
                                                  int port,
                                          @Value("${mail.smtp.auth}")
                                                  String auth,
                                          @Value("${mail.smtp.starttls.enable}")
                                                  String starttls,
                                          @Value("${mail.from}")
                                                  String from,
                                          @Value("${mail.username}")
                                                  String username,
                                          @Value("${mail.password}")
                                                  String password) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.smtp.starttls.enable", starttls);
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setProtocol(protocol);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }

}
