package com.example.studentssocial.util;

import com.example.studentssocial.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
@Component
public class EmailUtil {
    @Autowired
    private Environment env;

    public SimpleMailMessage composeEmail(final String subject, final String messageText, final User user) {

        final SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(messageText);
        message.setTo(user.getEmail());
        message.setFrom(env.getProperty("customer.care.email"));
        return message;
    }
}
