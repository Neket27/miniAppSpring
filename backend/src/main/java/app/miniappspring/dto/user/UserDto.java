package app.miniappspring.dto.user;

import app.miniappspring.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private Set<Role> roles;
}
