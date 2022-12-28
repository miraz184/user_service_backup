package com.stackroute.userservice.controller;

import com.stackroute.userservice.dto.AddUser;
import com.stackroute.userservice.dto.UserDetails;
import com.stackroute.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class RegistrationController {
    private IUserService service;

    @Autowired
    public RegistrationController(IUserService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/register")
    public ResponseEntity<UserDetails>  register(@RequestBody AddUser requestData) throws Exception {
        UserDetails userDetails = service.register(requestData);
        return new ResponseEntity<>(userDetails, HttpStatus.CREATED);
    }

    @GetMapping("/byEmail/{emailId}")
    public ResponseEntity<UserDetails> findByUserName(@PathVariable String emailId) throws Exception {
        UserDetails response = service.findByEmail(emailId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }







}
