package com.example.studentssocial.repository;


import com.example.studentssocial.entity.User;
import com.example.studentssocial.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
@Transactional
public interface UserDetailsRepository extends CrudRepository<UserDetails,Long> {

    Optional <UserDetails> findById(Long id);
}
