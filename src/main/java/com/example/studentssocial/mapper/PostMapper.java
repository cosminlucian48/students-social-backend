package com.example.studentssocial.mapper;

import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.repository.SubjectRepository;
import com.example.studentssocial.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostMapper {
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    public Post mapPostDtoToPost(PostDto postDto)
    {
        Subject subject = subjectRepository.findById(postDto.getSubjectId()).get();
        User user = userRepository.findById(postDto.getUserId()).get();
        Post post = new Post();


        post.setUser(user);
        post.setSubject(subject);

        post.setId(postDto.getId());
        post.setPostDate(postDto.getPostDate());
        post.setIsSticky(postDto.getIsSticky());
        post.setText(postDto.getText());
        post.setTitle(postDto.getTitle());
        post.setPostType(postDto.getPostType());
        post.setFileName(postDto.getFileName());
        post.setRealFileName(postDto.getRealFileName());

        return post;

    }

    public PostDto mapPostToPostDto(Post post)
    {
        PostDto postDto = new PostDto();

        postDto.setSubjectId(post.getSubject().getId());
        postDto.setUserId(post.getUser().getId());

        postDto.setId(post.getId());
        postDto.setPostDate(post.getPostDate());
        postDto.setIsSticky(post.getIsSticky());
        postDto.setText(post.getText());
        postDto.setTitle(post.getTitle());
        postDto.setPostType(post.getPostType());
        postDto.setProfileImage(post.getUser().getProfileImage());
        postDto.setFileName(post.getFileName());
        postDto.setRealFileName(post.getRealFileName());

        return postDto;
    }

    public PostDto mapJsonToPostDto(String postJons) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        PostDto postDto = mapper.readValue(postJons, PostDto.class);
        return postDto;
    }
}
