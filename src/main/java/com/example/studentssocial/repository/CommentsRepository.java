package com.example.studentssocial.repository;

import com.example.studentssocial.entity.Comments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CommentsRepository extends CrudRepository<Comments,Long> {

    Optional<Comments> findById(Long id);

}
