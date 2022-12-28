package com.stackroute.userservice.exception;

public class PasswordDoesNotMatchException extends Exception{
    public PasswordDoesNotMatchException(String msg){
        super(msg);
    }
}
