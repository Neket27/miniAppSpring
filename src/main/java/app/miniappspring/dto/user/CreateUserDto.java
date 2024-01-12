package app.miniappspring.dto.user;

import app.miniappspring.entity.Image;
import app.miniappspring.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Setter
@Getter
public class CreateUserDto {
    private String firstname;
    private String lastname;
    @NotBlank(message = "username = null")
    private String username;
    @NotBlank(message = "password user = null")
    private String password;
    private String email;
    private MultipartFile avatarMultipartFile;
    private Set<Role> roles;
}
