package com.example.studentssocial.mapper;

import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.entity.UserDetails;
import com.example.studentssocial.repository.SubjectRepository;
import com.example.studentssocial.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostMapper {
    private final UserDetailsRepository userDetailsRepository;
    private final SubjectRepository subjectRepository;

    public Post mapPostDtoToPost(PostDto postDto)
    {
        Subject subject = subjectRepository.findById(postDto.getSubjectId()).get();
        UserDetails userDetails = userDetailsRepository.findById(postDto.getUserDetailsId()).get();
        Post post = new Post();


        post.setUserDetails(userDetails);
        post.setSubject(subject);

        post.setId(postDto.getId());
        post.setPostDate(postDto.getPostDate());
        post.setIsSticky(postDto.getIsSticky());
        post.setText(postDto.getText());
        post.setTitle(postDto.getTitle());

        return post;

    }

    public PostDto mapPostToPostDto(Post post)
    {
        PostDto postDto = new PostDto();

        postDto.setSubjectId(post.getSubject().getId());
        postDto.setUserDetailsId(post.getUserDetails().getId());

        postDto.setId(post.getId());
        postDto.setPostDate(post.getPostDate());
        postDto.setIsSticky(post.getIsSticky());
        postDto.setText(post.getText());
        postDto.setTitle(post.getTitle());

        return postDto;
    }
}
