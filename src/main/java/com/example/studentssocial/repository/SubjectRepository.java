package com.example.studentssocial.repository;


import com.example.studentssocial.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SubjectRepository extends CrudRepository<Subject,Long> {
}
