package com.example.studentssocial.mapper;

import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public Post mapPostDtoToPost (PostDto postDto)
    {
        Post post = new Post();
        post.setSubjectID(postDto.getSubjectID());
        post.setId(postDto.getId());

        post.setPostDate(postDto.getPostDate());
        post.setUserID(postDto.getUserID());
        post.setIsSticky(postDto.getIsSticky());
        post.setText(postDto.getText());
        post.setTitle(postDto.getTitle());

        post.setPost(post);
        return post;

    }

    public PostDto mapPostToPostDto(Post post)
    {
        PostDto postDto = new PostDto();
        postDto.setSubjectID(post.getSubjectID());
        postDto.setId(post.getId());

        postDto.setPostDate(post.getPostDate());
        postDto.setUserID(post.getUserID());
        postDto.setIsSticky(post.getIsSticky());
        postDto.setText(post.getText());
        postDto.setTitle(post.getTitle());

        return postDto;
    }
}
