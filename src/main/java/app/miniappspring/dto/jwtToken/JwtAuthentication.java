package app.miniappspring.dto.jwtToken;

import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtAuthentication implements UserDetails {

    private boolean authenticated;
    private String username;
    private String firstName="NON";
    private Set<Role> roles= Set.of(Role.ADMIN);
    private final User user;


//    private final User user;
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
}

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//////////
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles;
//
//    }
//
//    @Override
//    public Object getCredentials() { return null; }
//
//    @Override
//    public Object getDetails() { return null; }
//
//    @Override
//    public Object getPrincipal() { return username; }
//
//    @Override
//    public boolean isAuthenticated() { return authenticated; }
//
//    @Override
//    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
//        this.authenticated = isAuthenticated;
//    }
//
//    @Override
//    public String getName() { return firstName; }

}