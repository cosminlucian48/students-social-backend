package com.example.studentssocial.service;

import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.entity.User;
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
    private final UserService userService;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService, PostMapper postMapper) {
        this.userService = userService;
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

    public PostDto updatePost(Post postDto)
    {
        Post post = postRepository.findById(postDto.getId()).get();
        post.setPostDate(postDto.getPostDate());
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setIsSticky(postDto.getIsSticky());
        post.setId(postDto.getId());
        /*post.setSubject(postDto.getSubject());
        post.setUserDetails(postDto.getUserDetails());*/
        return postMapper.mapPostToPostDto(post);


    }
    public PostDto savePost(PostDto postDto)
    {
        Post post = postMapper.mapPostDtoToPost(postDto);
        Post savedPost = postRepository.save(post);
        return postMapper.mapPostToPostDto(savedPost);
    }

    public List<PostDto> getPostsBySubjectId(Long subjectId){
        List<Post> allPosts = new ArrayList<>();
        postRepository.findAll().iterator().forEachRemaining(allPosts::add);
        List<PostDto> finalPosts = new ArrayList<>();
        for(Post post: allPosts){
            if(post.getSubject().getId() == subjectId){
                User user = userService.getUserById(post.getUser().getId());
                PostDto postDto = postMapper.mapPostToPostDto(post);
                postDto.setUsername(user.getEmail());
                finalPosts.add(postDto);
            }
        }
        return finalPosts;
    }

//    public PostDto updatePost(PostDto postDto){
//        Post post = postRepository.findById(postDto.getId()).get();
//        return postMapper.mapPostToPostDto(post);
//    }
}
