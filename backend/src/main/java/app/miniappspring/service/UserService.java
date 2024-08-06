package app.miniappspring.service;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.arguments.UpdateDataUserArgument;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.dto.user.UpdateAvatarUserDto;
import app.miniappspring.dto.user.UpdateDataUserDto;
import app.miniappspring.dto.user.UpdateUserDto;
import app.miniappspring.entity.User;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();

    CreateUserDto addUser(CreateUserArgument createUserArgument);

    UpdateDataUserDto updateDataUser(@NonNull UpdateDataUserArgument updateDataUserArgument);


    UpdateDataUserDto updateDataUser(UpdateUserDto updateUserDto);

    boolean updateUserAvatar(UpdateAvatarUserDto updateAvatarUserDto);

    List<User> getListUsers();

    User getById(Long id);

    User getByUsername(String username);

    boolean remove(String username);

    boolean isUsernameAlreadyInUse(String username);

    boolean isEmailAlreadyInUse(String email);

}
