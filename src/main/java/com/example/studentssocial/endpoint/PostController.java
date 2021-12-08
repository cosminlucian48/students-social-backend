package com.example.studentssocial.endpoint;


import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService){this.postService =postService;}

    @GetMapping
    public List<Post> getAllPosts(){return postService.getAllPosts();}

    @PostMapping
    public PostDto postPost(@RequestBody PostDto postDto){return postService.savePost(postDto);}
}
