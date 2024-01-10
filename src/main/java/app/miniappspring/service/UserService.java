package app.miniappspring.service;

import app.miniappspring.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();

    void addUser(User user);

    List<User> getListUsers();

    User getById(Long id);

    User getByUsername(String username);

    boolean remove(String username);

    @Transactional
    boolean isUsernameAlreadyInUse(String username);

    @Transactional
    boolean isEmailAlreadyInUse(String email);

}
