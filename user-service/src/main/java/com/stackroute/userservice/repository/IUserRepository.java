package com.stackroute.userservice.repository;

import com.stackroute.userservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUserRepository extends MongoRepository<User,String> {

    Optional<User> findByUserName(String username);


    Optional<User> findByEmailId(String emailId);


}
