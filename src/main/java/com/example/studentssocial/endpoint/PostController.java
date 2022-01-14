package com.example.studentssocial.endpoint;

import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.mapper.PostMapper;
import com.example.studentssocial.service.PostService;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/post")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PostController {


    private Logger logger = LoggerFactory.getLogger(SubjectController.class);
    private final PostService postService;
    private final PostMapper postMapper;

    @Autowired
    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

//    @GetMapping(value = "/subject/{subjectId}")
//    public List<PostDto> getPostsBySubjectId(@PathVariable("subjectId") Long subjectId){return postService.getPostsBySubjectId(subjectId);}

    @GetMapping(value = "/subject/{subjectId}")
    public List<PostDto> getPostsBySubjectId(@PathVariable("subjectId") Long subjectId) throws IOException {
        return postService.getPostsBySubjectId(subjectId);
    }


    @GetMapping(value = "/{id}")
    public Optional<Post> getPost(@PathVariable("id") Long id) {
        return postService.getPost(id);
    }

//    @GetMapping("/files")
//    public List<FileInfo> getListFiles() {
//
//        return postService.getAllFiles();
//    }

    @GetMapping(value="/files/{fileName:.+}/{subjectId}")
    public Resource getFile( @PathVariable String fileName, @PathVariable("subjectId") Long subjectId) {
//        System.out.println(fileName.);
        return postService.getFile(fileName, subjectId);

    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        logger.info("Deleted subject with {}", id);
        postService.deletePost(id);
    }

    @PutMapping("/{id}")
    public Post updatePost(@RequestBody Post post) {
        postService.updatePost(post);
        return post;
    }

    //    @PostMapping
//    public PostDto postPost(@RequestBody PostDto postDto) {
//        return postService.savePost(postDto,null);
//    }
    @PostMapping()
    public PostDto postPost(@RequestPart("post") String postJson, @RequestPart(value = "file", required = false) List<MultipartFile> files) {
//        System.out.println(postDto);
//        System.out.println(file);
        PostDto postDto;
        try {
            postDto = postMapper.mapJsonToPostDto(postJson);
            return postService.savePost(postDto, files);
        } catch (Exception e) {
            return null;
        }

    }

//    @PostMapping(value="/test")
//    public boolean postFile(@RequestPart("file") MultipartFile file) {
//        System.out.println(file);
//        return true;
////        return postService.savePost(postDto,file);
//    }

}
