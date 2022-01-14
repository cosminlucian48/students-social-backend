package com.example.studentssocial.service;

import com.example.studentssocial.dto.CommentsDto;
import com.example.studentssocial.dto.MessageDto;
import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.dto.EmailFieldsDto;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.entity.UserSubject;
import com.example.studentssocial.enums.UserEmailOptions;
import com.example.studentssocial.mail.EmailService;
import com.example.studentssocial.mapper.PostMapper;
import com.example.studentssocial.repository.PostRepository;
import com.example.studentssocial.repository.SubjectRepository;
import com.example.studentssocial.repository.UserRepository;
import com.example.studentssocial.repository.UserSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SendEmailService {

    private final String ADMIN_EMAIL = "cosminserver1234@gmail.com";
    private final ParseService parseService;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    private final UserSubjectRepository userSubjectRepository;
    private final EmailService emailService;
    private final MessageBodyService messageBodyService;
    private Logger logger = LoggerFactory.getLogger(SendEmailService.class);


    public void verifyAndSendEmail(MessageDto messageDto, UserEmailOptions userEmailOptions) {
        if (userEmailOptions.equals(UserEmailOptions.REGISTER)) {
            List<User> users = new ArrayList<>();
            List<User> users1 = userRepository.findUserByEmail(messageDto.getEmail());
            if (users1.size() > 0) {
                users.add(users1.get(0));
                sendEmailForType(messageDto, UserEmailOptions.REGISTER, users, "");
            }
            return;
        }

        String subjectName = getSubjectName(messageDto);
        List<String> taggedUserEmails = parseService.parseText(messageDto.getText());

        List<User> filteredTaggedUsers = new ArrayList<>();
        List<String> exceptedEmails = new ArrayList<>();
        taggedUserEmails.forEach((value) -> {
            List<User> users = userRepository.findUserByEmail(value);
            if (users.size() == 1) {
                User user = users.get(0);
                if (checkIfSendEmailToUser(user, UserEmailOptions.TAG)) {
                    filteredTaggedUsers.add(user);
                } else {
                    exceptedEmails.add(value);
                }
            }
        });

        exceptedEmails.forEach((value) -> {
            taggedUserEmails.remove(value);
        });

        if (filteredTaggedUsers.size() > 0) {
            sendEmailForType(messageDto, UserEmailOptions.TAG, filteredTaggedUsers, subjectName);
        }


        List<User> users = getUserEnrolledOnSubject(messageDto, userEmailOptions);
        List<User> filteredUsers = users.stream().filter(user -> !taggedUserEmails.contains(user.getEmail())).collect(Collectors.toList());
        if (filteredUsers.size() > 0) {
            sendEmailForType(messageDto, userEmailOptions, filteredUsers, subjectName);
        }
    }

    private String getSubjectName(MessageDto messageDto) {
        Optional<Subject> subject = subjectRepository.findById(messageDto.getSubjectId());
        if (subject.isPresent()) {
            return subject.get().getName();
        }
        return "";
    }

    private void sendEmailForType(MessageDto messageDto, UserEmailOptions userEmailOptions, List<User> users, String subjectName) {
        // sa adaug camp de post title, folosesc postId din messageDto
        EmailFieldsDto emailFieldsDto = new EmailFieldsDto();
        if (userEmailOptions.equals(UserEmailOptions.REGISTER)) {
            emailFieldsDto.setUserEmail(users.get(0).getEmail());
            emailFieldsDto.setFaculty(messageDto.getFaculty());
            emailFieldsDto.setUniversity(messageDto.getUniversity());
            emailFieldsDto.setFirstName(messageDto.getFirstName());
            emailFieldsDto.setLastName(messageDto.getLastName());
        } else {

            emailFieldsDto.setTaggerEmail(messageDto.getEmail());
            emailFieldsDto.setText(messageDto.getText());
            emailFieldsDto.setSubjectId(messageDto.getSubjectId());
            emailFieldsDto.setTextType(userEmailOptions.toString().toLowerCase());
            emailFieldsDto.setSubjectName(subjectName);
            if (userEmailOptions.equals(UserEmailOptions.COMMENT)) {
                emailFieldsDto.setPostTitle(messageDto.getPostTitle());
            }
        }


        String emailTitle = getEmailTitleByUserEmailOptions(userEmailOptions);
        users.forEach((user) -> {
            emailFieldsDto.setUserEmail(user.getEmail());
            String emailBody = getEmailBodyByUserEmailOptions(userEmailOptions, emailFieldsDto);
            emailService.sendEmail(ADMIN_EMAIL, user.getEmail(), emailBody, emailTitle);
            logger.info("Email sent to {}, type: {}", user.getEmail(), userEmailOptions);
        });
    }

    private List<User> getUserEnrolledOnSubject(MessageDto messageDto, UserEmailOptions userEmailOptions) {
        List<User> users = new ArrayList<>();
        List<UserSubject> userSubjects = userSubjectRepository.findAllBySubjectId(messageDto.getSubjectId());
        userSubjects.forEach((value) -> {
            User user = value.getUser();
            if (user != null && !user.getEmail().equals(messageDto.getEmail())) {
                if (checkIfSendEmailToUser(user, userEmailOptions)) {
                    users.add(user);
                }
            }
        });
//        return userSubjects;
        return users;
    }

    private String getEmailTitleByUserEmailOptions(UserEmailOptions userEmailOptions) {
        switch (userEmailOptions) {
            case TAG:
                return "You got tagged!";
            case POST:
                return "New Post!";
            case COMMENT:
                return "New Comment!";
            case REGISTER:
                return "Register Successful!";
            default:
                return "";
        }
    }

    private String getEmailBodyByUserEmailOptions(UserEmailOptions userEmailOptions, EmailFieldsDto emailFieldsDto) {
        switch (userEmailOptions) {
            case TAG:
                return messageBodyService.getTagMessageBody(emailFieldsDto);
            case POST:
                return messageBodyService.getPostMessageBody(emailFieldsDto);
            case COMMENT:
                return messageBodyService.getCommentMessageBody(emailFieldsDto);
            case REGISTER:
                return messageBodyService.getRegisterMessageBody(emailFieldsDto);
            default:
                return "";
        }
    }


    private boolean checkIfSendEmailToUser(User user, UserEmailOptions userEmailOptions) {
        switch (userEmailOptions) {
            case TAG:
                return user.getTagEmail();
            case POST:
                return user.getPostEmail();
            case COMMENT:
                return user.getCommentEmail();
            case REGISTER:
                return true;
            default:
                return false;
        }
    }

}
