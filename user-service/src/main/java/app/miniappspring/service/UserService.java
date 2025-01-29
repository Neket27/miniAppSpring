package app.miniappspring.service;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.arguments.UpdateDataUserArgument;
import app.miniappspring.dto.user.*;
import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import lombok.NonNull;

import java.util.List;
import java.util.Set;

public interface UserService {

    CreateUserDto addUser(@NonNull CreateUserArgument createUserArgument);

    UpdateDataUserDto updateDataUser(@NonNull UpdateDataUserArgument updateDataUserArgument);


    UpdateDataUserDto updateDataUser(UpdateUserDto updateUserDto);

    boolean updateUserAvatar(@NonNull UpdateAvatarUserDto updateAvatarUserDto);

    List<UserDto> getListUsers();

    User getById(@NonNull Long id);

    User getByUsername(String username);

    boolean remove(String username);

    boolean isUsernameAlreadyInUse(String username);

    boolean isEmailAlreadyInUse(@NonNull String email);

    Set<Role> getListUserRole(String username);

}
