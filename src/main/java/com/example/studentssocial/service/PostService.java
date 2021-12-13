package com.example.studentssocial.service;

import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.mapper.PostMapper;
import com.example.studentssocial.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postMapper = postMapper;
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        postRepository.findAll().iterator().forEachRemaining(posts::add);
        return posts;
    }

    public Optional<Post> getPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost;
    }

    public void deletePost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            postRepository.delete(post.get());
        }
    }

    public Post updatePost(Long id, Post postUpdated) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            postUpdated.setId(id);
            return postRepository.save(postUpdated);

        }
        return postUpdated;
    }
    public PostDto savePost(PostDto postDto)
    {
        Post post = postMapper.mapPostDtoToPost(postDto);
        Post savedPost = postRepository.save(post);
        return postMapper.mapPostToPostDto(savedPost);
    }

}
