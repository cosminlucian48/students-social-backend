package com.example.studentssocial.mail;

import com.example.studentssocial.entity.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private Configuration configuration;

//    public void sendSimpleMessage(
//            String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("noreply@baeldung.com");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        emailSender.send(message);
//    }

    public void registerHtmlMail(User user){
        MimeMessage message = emailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Template t = configuration.getTemplate("register-email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t,user);
            helper.setTo(user.getEmail());
            helper.setSubject("Te ai inregistrat");
            helper.setText(html,true);
            helper.setFrom("cosminserver1234@gmail.com");
            emailSender.send(message);


        }catch (MessagingException | IOException | TemplateException e){

        }
    }
}
