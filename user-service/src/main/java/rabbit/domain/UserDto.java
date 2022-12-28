package rabbit.domain;

import com.stackroute.userservice.entity.Role;
import lombok.Data;

@Data
public class UserDto {
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private Role role;
}
