package com.example.studentssocial.repository;


import com.example.studentssocial.entity.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
//    List<Post> findAllByOrderByIdDesc();
////    List<Post> findByOrderByPostDateDesc();
////    List<Post> all
////    List<Post> findByOrderByPostDateDesc();
////    List<Post> findAllOrderByPostDateDesc();
    List<Post> findAllByOrderByPostDateAsc();
}
