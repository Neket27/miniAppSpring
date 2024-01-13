package app.miniappspring.dto.user;

import app.miniappspring.entity.Image;
import app.miniappspring.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UpdateUserDto {
    private String firstname;
    private String lastname;
    @NotBlank(message = "username = null")
    private String username;
    @NotBlank(message = "password user = null")
    private String password;
    private String email;
    private Image avatar;
    private Set<Role> roles;
}