package com.example.studentssocial.service;

import com.example.studentssocial.dto.CommentsDto;
import com.example.studentssocial.entity.Comments;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.mapper.CommentsMapper;
import com.example.studentssocial.repository.CommentsRepository;
import com.example.studentssocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;
    private final CommentsMapper commentsMapper;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository, UserRepository userRepository, CommentsMapper commentsMapper) {
        this.userRepository = userRepository;
        this.commentsMapper = commentsMapper;
        this.commentsRepository = commentsRepository;
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
        return finalComments;
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
        return commentsMapper.mapCommentsToCommentsDto(savedComments);
    }


}
