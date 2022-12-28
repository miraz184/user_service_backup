package com.stackroute.userservice.exception;

public class EmailAlreadyExists extends Exception{
    public EmailAlreadyExists(String msg){
        super(msg);
    }
}
