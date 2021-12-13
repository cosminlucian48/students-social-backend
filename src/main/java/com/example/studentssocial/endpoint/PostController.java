package com.example.studentssocial.endpoint;

import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.example.studentssocial.mapper.PostMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/post")
public class PostController {


    private Logger logger = LoggerFactory.getLogger(SubjectController.class);
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){this.postService =postService;}

    @GetMapping
    public List<Post> getAllPosts(){return postService.getAllPosts();}

    @GetMapping(value = "/{id}")
    public Optional<Post> getSubject(@PathVariable("id") Long id) { return postService.getPost(id); }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id) {
        logger.info("Deleted subject with {}", id);
        postService.deletePost(id);
    }
    @PutMapping("/{id}")
        public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        postService.updatePost(id, post);
        logger.info("Updated Post {}", post);
        return post;
    }
    @PostMapping
    public PostDto postPost(@RequestBody PostDto postDto){return postService.savePost(postDto);}
}
