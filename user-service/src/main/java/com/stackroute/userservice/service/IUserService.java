package com.stackroute.userservice.service;

import com.stackroute.userservice.dto.AddUser;
import com.stackroute.userservice.dto.UserDetails;
import com.stackroute.userservice.exception.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface IUserService {
    UserDetails register(@Valid AddUser requestData) throws InvalidArgumentException, EmailAlreadyExists, UserNotFoundException, PasswordDoesNotMatchException, MobileNoNotValidException;

    UserDetails findByUsername(String username) throws InvalidArgumentException,  UserNotFoundException;

    UserDetails findByEmail(String email) throws InvalidArgumentException,UserNotFoundException;


}
