package com.example.studentssocial.service;

import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.mapper.PostMapper;
import com.example.studentssocial.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository, PostMapper postMapper)
    {
        this.postMapper = postMapper;
        this.postRepository =postRepository;
    }

    public List<Post>getAllPosts()
    {
        List<Post> posts = new ArrayList<>();
        postRepository.findAll().iterator().forEachRemaining(posts::add);
        return posts;
    }

    public PostDto savePost(PostDto postDto)
    {
        Post post = postMapper.mapPostDtoToPost(postDto);
        Post savedPost = postRepository.save(post);
        return postMapper.mapPostToPostDto(savedPost);

    }
}
