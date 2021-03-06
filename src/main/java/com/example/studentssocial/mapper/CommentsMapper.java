package com.example.studentssocial.mapper;

import com.example.studentssocial.dto.CommentsDto;
import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Comments;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.repository.PostRepository;
import com.example.studentssocial.repository.SubjectRepository;
import com.example.studentssocial.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CommentsMapper {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Comments mapCommentsDtoToComments(CommentsDto commentsDto)
    {
        Post post = postRepository.findById(commentsDto.getPostId()).get();
        User user = userRepository.findById(commentsDto.getUserId()).get();
        Comments comments = new Comments();

        comments.setPost(post);
        comments.setUser(user);


        comments.setId(commentsDto.getId());
        comments.setCommentsDate(commentsDto.getCommentsDate());
        comments.setText(commentsDto.getText());
        comments.setCommentType(commentsDto.getCommentType());
        comments.setFileName(commentsDto.getFileName());



        return comments;
    }

    public CommentsDto mapCommentsToCommentsDto(Comments comments)
    {
        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setPostId(comments.getPost().getId());
        commentsDto.setUserId(comments.getUser().getId());

        commentsDto.setId(comments.getId());
        commentsDto.setCommentsDate(comments.getCommentsDate());
        commentsDto.setText(comments.getText());
        commentsDto.setEmail(comments.getUser().getEmail());
        commentsDto.setCommentType(comments.getCommentType());
        commentsDto.setProfileImage(comments.getUser().getProfileImage());
        commentsDto.setFileName(comments.getFileName());

        return commentsDto;
    }
    public CommentsDto mapJsonToCommentDto(String commentJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        CommentsDto commentsDto = mapper.readValue(commentJson, CommentsDto.class);
        return commentsDto;
    }

}
