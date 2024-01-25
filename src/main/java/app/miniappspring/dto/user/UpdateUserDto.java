package app.miniappspring.dto.user;

import app.miniappspring.entity.ImageUser;
import app.miniappspring.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class UpdateUserDto {
    private String firstname;
    private String lastname;
    @NotBlank(message = "поль username не можнт быть null")
    private String username;
    @NotBlank(message = "поле password не может быть null")
    private String password;
    private String email;
    private ImageUser avatar;
    @NotBlank(message = "поле roles не может быть null")
    private Set<Role> roles;
}