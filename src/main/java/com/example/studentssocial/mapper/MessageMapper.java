package com.example.studentssocial.mapper;

import com.example.studentssocial.dto.CommentsDto;
import com.example.studentssocial.dto.MessageDto;
import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MessageMapper {

    private final PostRepository postRepository;

    public MessageDto fromPostDtoToMessageDto(PostDto postDto) {
        MessageDto messageDto = new MessageDto();
        messageDto.setEmail(postDto.getEmail());
        messageDto.setPostId(postDto.getId());
        messageDto.setDate(postDto.getPostDate());
        messageDto.setText(postDto.getText());
        messageDto.setUserId(postDto.getUserId());
        messageDto.setSubjectId(postDto.getSubjectId());
        return messageDto;
    }

    public MessageDto fromCommentDtoToMessageDto(CommentsDto commentsDto) {
        MessageDto messageDto = new MessageDto();
        messageDto.setEmail(commentsDto.getEmail());
        messageDto.setPostId(commentsDto.getId());
        messageDto.setDate(commentsDto.getCommentsDate());
        messageDto.setText(commentsDto.getText());
        messageDto.setUserId(commentsDto.getUserId());
        Post post = postRepository.getById(commentsDto.getPostId());
        messageDto.setPostTitle(post.getTitle());

        messageDto.setSubjectId(post.getSubject().getId());

        return messageDto;
    }

    public MessageDto fromUserToMessageDto(User user) {
        MessageDto messageDto = new MessageDto();
        messageDto.setEmail(user.getEmail());
        messageDto.setFaculty(user.getFaculty());
        messageDto.setUniversity(user.getUniversity());
        messageDto.setFirstName(user.getFirstName());
        messageDto.setLastName(user.getLastName());

        return messageDto;
    }
}
