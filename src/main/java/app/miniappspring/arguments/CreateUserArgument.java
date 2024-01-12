package app.miniappspring.arguments;

import app.miniappspring.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@NoArgsConstructor

public class CreateUserArgument {
    private String firstname;
    private String lastname;
    @NotBlank(message = "username = null")
    private String username;
    @NotBlank(message = "password user = null")
    private String password;
    private String email;
    private MultipartFile avatarMultipartFile;
    private Set<Role> roles;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public MultipartFile getAvatarMultipartFile() {
        return avatarMultipartFile;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatarMultipartFile(MultipartFile avatarMultipartFile) {
        this.avatarMultipartFile = avatarMultipartFile;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
