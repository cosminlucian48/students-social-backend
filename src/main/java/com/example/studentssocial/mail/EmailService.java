package com.example.studentssocial.mail;

import com.example.studentssocial.dto.EmailFieldsDto;
import com.example.studentssocial.endpoint.SubjectController;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.repository.UserRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final String ADMIN_EMAIL = "cosminserver1234@gmail.com";

    private final JavaMailSender emailSender;

    private final Configuration configuration;

    private final UserRepository userRepository;
    public void sendEmail(String sendFrom, String sendTo, String mailBody, String title) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setTo(sendTo);
            helper.setSubject(title);
            helper.setText(mailBody, true);
            helper.setFrom(sendFrom);
            emailSender.send(message);

        } catch (MessagingException e) {

        }
    }


    public void sendRegisterEmail(User user) {
        try {
            Template t = configuration.getTemplate("register-email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, user);

            this.sendEmail(ADMIN_EMAIL, user.getEmail(), html, "Te-ai inregistrat!");
        } catch (IOException | TemplateException e) {

        }
    }


}
