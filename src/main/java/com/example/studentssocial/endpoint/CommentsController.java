package com.example.studentssocial.endpoint;

import com.example.studentssocial.dto.CommentsDto;
import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.dto.SubjectDto;
import com.example.studentssocial.entity.Comments;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.service.CommentsService;
import com.example.studentssocial.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/comments")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CommentsController {
    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping
    public List<Comments> getAllComments() {
        return commentsService.getAllComments();
    }

    @PostMapping
    public CommentsDto postComments(@RequestBody CommentsDto commentsDto){
        return commentsService.saveComments(commentsDto);}


    @GetMapping(value = "/post/{postId}")
    public List<CommentsDto> getCommentsByPostId(@PathVariable("postId") Long postId){return commentsService.getCommentsbyPostId(postId);}
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
    public Optional<Comments> getComments(@PathVariable("id") Long id) { return commentsService.getComments(id); }

}
