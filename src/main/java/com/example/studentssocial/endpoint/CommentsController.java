package com.example.studentssocial.endpoint;

import com.example.studentssocial.dto.CommentsDto;
import com.example.studentssocial.entity.Comments;
import com.example.studentssocial.mapper.CommentsMapper;
import com.example.studentssocial.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/comments")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CommentsController {
    private final CommentsService commentsService;
    private final CommentsMapper commentsMapper;

    @Autowired
    public CommentsController(CommentsService commentsService, CommentsMapper commentsMapper) {
        this.commentsService = commentsService;
        this.commentsMapper = commentsMapper;
    }

    @GetMapping
    public List<Comments> getAllComments() {
        return commentsService.getAllComments();
    }

    @PostMapping
    public CommentsDto postComments(@RequestPart("comment") String commentJson, @RequestPart(value = "file", required = false) List<MultipartFile> files) {
        CommentsDto commentsDto;
        try {
            commentsDto = commentsMapper.mapJsonToCommentDto(commentJson);
            return commentsService.saveComments(commentsDto, files);
        } catch (Exception e) {
            return null;
        }

    }


    @GetMapping(value = "/post/{postId}")
    public List<CommentsDto> getCommentsByPostId(@PathVariable("postId") Long postId) {
        return commentsService.getCommentsbyPostId(postId);
    }

    @DeleteMapping("/{id}")
    public void deleteComments(@PathVariable Long id) {
        commentsService.deleteComments(id);
    }

    @PutMapping("/{id}")
    public Comments updateComments(@RequestBody Comments comments) {
        commentsService.updateComments(comments);
        return comments;
    }

    @GetMapping(value = "/{id}")
    public Optional<Comments> getComments(@PathVariable("id") Long id) {
        return commentsService.getComments(id);
    }

}
