package com.stackroute.userservice.dto;

import com.stackroute.userservice.entity.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddUser {

    @Email
    @NotBlank(message = "Email id can't be blank or empty")
    private String emailId;

    @Size(min = 2,max=20,message = "name should between 2 and 20")
    @NotBlank(message = "name can't be blank or empty")
    private String userName;

    @NotBlank(message = "Email id can't be blank or empty")
    private String userFirstName;
    @NotBlank(message = "Email id can't be blank or empty")
    private String userLastName;


    @NotBlank(message = "Password can't be blank or empty")
    private String password;


    @NotBlank(message = "ConfirmPassword can't be blank or empty")
    private String confirmPassword;

    @NotBlank(message = "Mobile no. can't be blank or empty")
    private String mobileNo;


    @NotBlank(message = "Role can't be blank or empty")
    private Role role;

    public String getUserName() {
        return userName;
    }

}

