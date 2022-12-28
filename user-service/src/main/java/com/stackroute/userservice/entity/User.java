package com.stackroute.userservice.entity;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;


@NoArgsConstructor
@Data
@Document("user_registration")
public class User {
    @Id
    //private String id;
    @Field(name="email_id")
    private String emailId;

    @Field(name="user_name")
    private String userName;
    
    @Field(name="first_name")
    private String userFirstName;
    
    @Field(name="last_name")
    private String userLastName;
    
    @Field(name="password")
    private String password;

    @Field(name="confirm_password")
    private String confirmPassword;

    @Field(name="mobile_no")
    private String mobileNo;

    @Field(name="role")
    private Role role;
    
    

    public User(String emailId, String userName, String password, String confirmPassword, String mobileNo, Role role) {
        this.emailId = emailId;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.mobileNo = mobileNo;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return emailId.equals(user.emailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId);
    }

    @Override
    public String toString() {
        return "User{" +
               /* "id='" + id + '\'' +*/
                ", emailId='" + emailId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", role=" + role +
                '}';
    }
}
