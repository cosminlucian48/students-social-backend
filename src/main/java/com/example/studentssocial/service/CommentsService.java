package com.example.studentssocial.service;

import com.example.studentssocial.dto.CommentsDto;
import com.example.studentssocial.dto.MessageDto;
import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Comments;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.enums.UserEmailOptions;
import com.example.studentssocial.mapper.CommentsMapper;
import com.example.studentssocial.mapper.MessageMapper;
import com.example.studentssocial.repository.CommentsRepository;
import com.example.studentssocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final SendEmailService sendEmailService;
    private final UserRepository userRepository;
    private final CommentsMapper commentsMapper;
    private final MessageMapper messageMapper;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository, SendEmailService sendEmailService, UserRepository userRepository, CommentsMapper commentsMapper, MessageMapper messageMapper) {
        this.sendEmailService = sendEmailService;
        this.userRepository = userRepository;
        this.commentsMapper = commentsMapper;
        this.commentsRepository = commentsRepository;
        this.messageMapper = messageMapper;
    }

    public List<Comments> getAllComments() {
        List<Comments> comments = new ArrayList<>();
        commentsRepository.findAll().iterator().forEachRemaining(comments::add);
        return comments;
    }

    public Optional<Comments> getComments(Long id) {
        Optional<Comments> optionalComments = commentsRepository.findById(id);
        return optionalComments;
    }

    public void deleteComments(Long id) {
        Optional<Comments> comments = commentsRepository.findById(id);
        if (comments.isPresent()) {
            commentsRepository.delete(comments.get());
        }
    }

    public List<CommentsDto> getCommentsbyPostId(Long postId){
        List<Comments> allComments = new ArrayList<>();
        commentsRepository.findAll().iterator().forEachRemaining(allComments::add);
        List<CommentsDto> finalComments = new ArrayList<>();
        for(Comments comments: allComments){
            if(comments.getPost().getId() == postId){
                finalComments.add(commentsMapper.mapCommentsToCommentsDto(comments));
            }
        }

        Comparator<CommentsDto> comparator = (CommentsDto o1, CommentsDto o2) -> {
            if (o1.getCommentsDate().before(o2.getCommentsDate())) {
                return 1;
            } else if (o1.getCommentsDate().equals(o2.getCommentsDate())) {
                return 0;
            } else {
                return -1;
            }

        };
        ArrayList<CommentsDto> comments = finalComments.stream().sorted(comparator).collect(Collectors.toCollection(ArrayList::new));


        return comments;
    }

    public CommentsDto updateComments(Comments commentsDto)
    {
        Comments comments = commentsRepository.findById(commentsDto.getId()).get();
        comments.setCommentsDate(commentsDto.getCommentsDate());
        comments.setText(commentsDto.getText());

        return commentsMapper.mapCommentsToCommentsDto(comments);


    }

    public CommentsDto saveComments(CommentsDto commentsDto)
    {
        Comments comments = commentsMapper.mapCommentsDtoToComments(commentsDto);

        Optional<User> user = userRepository.findById(commentsDto.getUserId());
        if(user.isPresent()){
            comments.setCommentType(user.get().getAuthorities());
        }
        Comments savedComments = commentsRepository.save(comments);

        MessageDto messageDto = messageMapper.fromCommentDtoToMessageDto(commentsDto);

        Thread thread = new Thread(() -> sendEmailService.verifyAndSendEmail(messageDto, UserEmailOptions.COMMENT));
        thread.start();
        return commentsMapper.mapCommentsToCommentsDto(savedComments);
    }

    public void deleteCommentsWithPostId(Long postId){
        commentsRepository.deleteAllByPostId(postId);
    }


}
