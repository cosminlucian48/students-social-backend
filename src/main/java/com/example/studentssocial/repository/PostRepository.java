package com.example.studentssocial.repository;


import com.example.studentssocial.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PostRepository extends CrudRepository<Post, Long> {
}
