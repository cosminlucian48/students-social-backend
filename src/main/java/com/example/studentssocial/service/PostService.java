package com.example.studentssocial.service;

import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.mapper.PostMapper;
import com.example.studentssocial.repository.PostRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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
//        postRepository.findAllByOrderByPostDateDesc().iterator().forEachRemaining(posts::add);
        posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
//        postRepository.findAll().iterator().forEachRemaining(posts::add);

        posts.sort(new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                if (o1.getPostDate().compareTo(o2.getPostDate()) < 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });


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

    public PostDto updatePost(Post postDto) {
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

    public PostDto savePost(PostDto postDto) {
        List<User> users = userService.getUsersByEmail(postDto.getEmail());
        postDto.setUserId(users.get(0).getId());
        postDto.setPostType(users.get(0).getAuthorities());
        Post post = postMapper.mapPostDtoToPost(postDto);

        Post savedPost = postRepository.save(post);
        return postMapper.mapPostToPostDto(savedPost);
    }

    public List<PostDto> getPostsBySubjectId(Long subjectId) {
        List<Post> allPosts = new ArrayList<>();
        postRepository.findAll().iterator().forEachRemaining(allPosts::add);
        List<PostDto> finalPosts = new ArrayList<>();
        for (Post post : allPosts) {
            if (post.getSubject().getId() == subjectId) {
                User user = userService.getUserById(post.getUser().getId());
                PostDto postDto = postMapper.mapPostToPostDto(post);
                postDto.setEmail(user.getEmail());
                finalPosts.add(postDto);
            }
        }
//        finalPosts.sort(new Comparator<Post>() {
//            @Override
//            public int compare(Post o1, Post o2) {
//                if(o1.getPostDate().compareTo(o2.getPostDate())<0){
//                    return 0;
//                }else{
//                    return 1;
//                }
//            }
//        });

//        finalPosts.sort(new Comparator<PostDto>() {
//            @Override
//            public int compare(PostDto o1, PostDto o2) {
//                if (o1.getPostDate().compareTo(o2.getPostDate()) < 0) {
//                    return 0;
//                } else {
//                    return 1;
//                }
//            }
//        });

        Collections.sort(finalPosts, new Comparator<PostDto>() {
            @Override
            public int compare(PostDto o1, PostDto o2) {
                if (o1.getPostDate().before(o2.getPostDate())) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return finalPosts;
    }

//    public PostDto updatePost(PostDto postDto){
//        Post post = postRepository.findById(postDto.getId()).get();
//        return postMapper.mapPostToPostDto(post);
//    }
}
