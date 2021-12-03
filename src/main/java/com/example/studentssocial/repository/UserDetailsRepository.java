package com.example.studentssocial.repository;


import com.example.studentssocial.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserDetailsRepository extends CrudRepository<UserDetails,Long> {
}
