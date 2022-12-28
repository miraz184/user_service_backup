package com.stackroute.userservice.controller;


import com.stackroute.userservice.exception.InvalidArgumentException;
import com.stackroute.userservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class CentralizedExceptionHandler {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String handleProductNotFound(UserNotFoundException e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            InvalidArgumentException.class,
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class
    })
    public String handleInvalidArgument(Exception e){
        return "invalid arguments";
    }


}
