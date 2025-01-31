package app.miniappspring.dto.user;

import app.miniappspring.entity.Image;
import app.miniappspring.entity.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserDto(
        Long id,
        String firstname,
        String lastname,
        String username,
        String email,
        Image avatar,
        Set<Role> roles
) {}
