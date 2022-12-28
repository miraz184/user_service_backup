package com.stackroute.userservice.service;


import com.stackroute.userservice.dto.AddUser;
import com.stackroute.userservice.dto.UserDetails;
import com.stackroute.userservice.entity.User;
import com.stackroute.userservice.exception.*;
import com.stackroute.userservice.rabbitmqConfig.Producer;
import com.stackroute.userservice.repository.IUserRepository;
import com.stackroute.userservice.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository userrepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Producer producer;

    private UserUtil userUtil;

    @Autowired
    public UserServiceImpl(IUserRepository userrepo , UserUtil userUtil, Producer producer){
        this.userrepo=userrepo;
        this.userUtil=userUtil;
        this.producer = producer;
    }

    private Random random=new Random();


    @Override
    public UserDetails register(AddUser requestData) throws EmailAlreadyExists, UserNotFoundException, PasswordDoesNotMatchException, InvalidArgumentException, MobileNoNotValidException {
        passwordValidator(requestData.getPassword(),requestData.getConfirmPassword());
        contactNumberValidator(requestData.getMobileNo());
        User user = new User();
        user.setEmailId(requestData.getEmailId());
        user.setUserName(requestData.getUserName());
        user.setUserFirstName(requestData.getUserFirstName());
        user.setUserLastName(requestData.getUserLastName());
        user.setPassword(passwordEncoder.encode(requestData.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(requestData.getConfirmPassword()));
        user.setMobileNo(requestData.getMobileNo());
        user.setRole(requestData.getRole());

        Optional<User> userEntry = userrepo.findByUserName(user.getUserName());
        Optional<User> userEmail = userrepo.findByEmailId(user.getEmailId());

        if(userEntry.isPresent()){
            throw new UserNotFoundException("Username already exists !!");
        } else if (userEmail.isPresent()) {
            throw new EmailAlreadyExists("Email Id already Exists !!");
        }
        user=userrepo.save(user);
        UserDetails desired=userUtil.toUserDetails(user);
        producer.sendMessageToRabbitmq(user);
        return desired;
    }


    @Override
    public UserDetails findByUsername(String username) throws  UserNotFoundException {

        Optional<User> optional = userrepo.findByUserName(username);
        if(optional.isEmpty()){
            throw new UserNotFoundException("user not found by username= "+username);
        }
        return userUtil.toUserDetails(optional.get());
    }

    @Override
    public UserDetails findByEmail(String email) throws  UserNotFoundException {
        Optional<User> optional = userrepo.findByEmailId(email);
        if(optional.isEmpty()){
            throw new UserNotFoundException("User not found by emailId= "+email);
        }
        return userUtil.toUserDetails(optional.get());
    }



    public void passwordValidator(String password, String confirmPassword) throws PasswordDoesNotMatchException {
        if(!password.equals(confirmPassword)){
            throw new PasswordDoesNotMatchException("Password and ConfirmPassword Fields does not match");
        }
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public void contactNumberValidator(String mobileNo) throws MobileNoNotValidException {
        if (mobileNo.length()!=10){
            throw new MobileNoNotValidException("Mobile number is less that 10 digits Please enter valid mobile number");
        }else {
            return;
        }
    }

}
