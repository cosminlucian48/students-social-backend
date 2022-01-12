package com.example.studentssocial.service;

import com.example.studentssocial.dto.EmailFieldsDto;
import com.example.studentssocial.entity.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MessageBodyService {

    private final Configuration configuration;

    public String getRegisterMessageBody(EmailFieldsDto emailFieldsDto) {
        try {
            Template t = configuration.getTemplate("register-email-template.ftl");
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, emailFieldsDto);

        } catch (IOException | TemplateException e) {

        }
        return "";
    }


    public String getTagMessageBody(EmailFieldsDto emailFieldsDto) {
        try {
            Template t = configuration.getTemplate("tag-email-template.ftl");
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, emailFieldsDto);
        } catch (IOException | TemplateException e) {
        }
        return "";
    }

    public String getPostMessageBody(EmailFieldsDto emailFieldsDto) {
        try {
            Template t = configuration.getTemplate("post-email-template.ftl");
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, emailFieldsDto);
        } catch (IOException | TemplateException e) {
        }
        return "";
    }

    public String getCommentMessageBody(EmailFieldsDto emailFieldsDto) {
        try {
            Template t = configuration.getTemplate("comment-email-template.ftl");
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, emailFieldsDto);
        } catch (IOException | TemplateException e) {
        }
        return "";
    }

}
