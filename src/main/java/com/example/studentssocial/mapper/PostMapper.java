package com.example.studentssocial.mapper;

import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.entity.User;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public Post mapPostDtoToPost (PostDto postDto)
    {
        Post post = new Post();
        User user = new User();
        user.setId(postDto.getUserID());
        post.setSubjectID(postDto.getSubjectID());
        post.setId(postDto.getId());

        post.setPostDate(postDto.getPostDate());
        post.setIsSticky(postDto.getIsSticky());
        post.setText(postDto.getText());
        post.setTitle(postDto.getTitle());
        post.setUser(user);

        return post;

    }

    public PostDto mapPostToPostDto(Post post)
    {
        PostDto postDto = new PostDto();
        postDto.setSubjectID(post.getSubjectID());
        postDto.setId(post.getId());

        postDto.setPostDate(post.getPostDate());
        postDto.setUserID(post.getUser().getId());
        postDto.setIsSticky(post.getIsSticky());
        postDto.setText(post.getText());
        postDto.setTitle(post.getTitle());

        return postDto;
    }
}
