package com.example.studentssocial.repository;


import com.example.studentssocial.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findUserByEmail(String email);

    Optional<User> findById(Long id);

}
