package com.example.studentssocial.repository;


import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface SubjectRepository extends CrudRepository<Subject,Long> {

    Optional<Subject> findById(Long id);
}
