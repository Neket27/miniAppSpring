package app.miniappspring.dto.jwtToken;

import app.miniappspring.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@AllArgsConstructor
public class SignUpRequest {

    private String firstname;
    private String lastname;
    @NotBlank(message = "username = null")
    private String username;
    @NotBlank(message = "password user = null")
    private String password;
    private String email;
    private Set<Role> roles;
}
