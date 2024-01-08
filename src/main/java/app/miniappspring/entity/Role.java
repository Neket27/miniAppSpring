package app.miniappspring.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

//@RequiredArgsConstructor
public enum Role  {
//    ADMIN("  ADMIN"),
//    USER("USER");

//    private final String vale;
//    @Override
//    public String getAuthority() {
//        return null;
//    }

    ROLE_USER,
    ROLE_ADMIN
}
