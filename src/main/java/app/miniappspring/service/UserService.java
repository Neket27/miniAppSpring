package app.miniappspring.service;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.arguments.UpdateUserArgument;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.dto.user.UpdateAvatarUserDto;
import app.miniappspring.dto.user.UpdateUserDto;
import app.miniappspring.entity.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();

    CreateUserDto addUser(CreateUserArgument createUserArgument);

    @Transactional
    UpdateUserDto updateDataUser(@NonNull UpdateUserArgument updateUserArgument);

    @Transactional
    UpdateUserDto updateDataUser(UpdateUserDto updateUserDto);

    @Transactional
    boolean updateUserAvatar(UpdateAvatarUserDto updateAvatarUserDto);

    List<User> getListUsers();

    User getById(Long id);

    User getByUsername(String username);

    boolean remove(String username);

    @Transactional
    boolean isUsernameAlreadyInUse(String username);

    @Transactional
    boolean isEmailAlreadyInUse(String email);

}
