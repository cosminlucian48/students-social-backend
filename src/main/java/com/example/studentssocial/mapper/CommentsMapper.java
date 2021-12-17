package com.example.studentssocial.mapper;

import com.example.studentssocial.dto.CommentsDto;
import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Comments;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.repository.PostRepository;
import com.example.studentssocial.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CommentsMapper {

    private final PostRepository postRepository;

    public Comments mapCommentsDtoToComments(CommentsDto commentsDto)
    {
        Post post = postRepository.findById(commentsDto.getPostId()).get();
        Comments comments = new Comments();

        comments.setPost(post);

        comments.setId(commentsDto.getId());
        comments.setCommentsDate(commentsDto.getCommentsDate());
        comments.setText(commentsDto.getText());


        return comments;
    }

    public CommentsDto mapCommentsToCommentsDto(Comments comments)
    {
        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setPostId(comments.getPost().getId());

        commentsDto.setId(comments.getId());
        commentsDto.setCommentsDate(comments.getCommentsDate());
        commentsDto.setText(comments.getText());

        return commentsDto;
    }
}
