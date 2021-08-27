/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khiem.blog_ca_nhan.Config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author Administrator
 */
@Configuration
@ComponentScan({"com.khiem.blog_ca_nhan"})
public class MailConfig {
    @Autowired
    private Environment env;
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("config.mail.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("config.mail.port")));
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setUsername(env.getProperty("config.mail.username"));
        mailSender.setPassword(env.getProperty("config.mail.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
