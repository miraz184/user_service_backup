package com.stackroute.userservice.util;

import com.stackroute.userservice.dto.UserDetails;
import com.stackroute.userservice.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserUtil {


    public UserDetails toUserDetails(User user){
        UserDetails desired=new UserDetails();
        //desired.setId(user.getId());
        desired.setUserName(user.getUserName());
        desired.setEmailId(user.getEmailId());
        desired.setFirstName(user.getUserFirstName());
        desired.setLastName(user.getUserLastName());
        desired.setPassword(user.getPassword());
        desired.setConfirmPassword(user.getConfirmPassword());
        desired.setMobileNo(user.getMobileNo());
        desired.setRole(user.getRole());

        return desired;
    }

    public List<UserDetails> toUserDetailsList(Collection<User> customers){
       return customers.stream()
                .map(this::toUserDetails)
                .collect(Collectors.toList());
    }

   /* public Role getRoleType(String type) throws InvalidArgumentException {
        if(type==null || type.isBlank()){
            throw new InvalidArgumentException("Type cannot be null or empty");
        }
        Role[] role = Role.values();
        Optional<Role> optional = Stream.of(role).filter(role1 -> role1.toString().equalsIgnoreCase(type)).findAny();
        if (optional.isEmpty()){
            throw new InvalidArgumentException("Role is not valid");
        }
        return optional.get();
    }
*/


}
