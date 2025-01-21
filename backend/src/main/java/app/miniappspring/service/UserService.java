package app.miniappspring.service;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.arguments.UpdateDataUserArgument;
import app.miniappspring.dto.delivey.UpdateDeliveryDataUser;
import app.miniappspring.dto.user.*;
import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserDetailsService getUserDetailsService();

    CreateUserDto addUser(CreateUserArgument createUserArgument);

    UpdateDataUserDto updateDataUser(@NonNull UpdateDataUserArgument updateDataUserArgument);

    UpdateDataUserDto updateDataUser(UpdateUserDto updateUserDto);

    boolean updateUserAvatar(UpdateAvatarUserDto updateAvatarUserDto);

    List<UserDto> getListUsers();

    User getById(Long id);

    User getByUsername(String username);

    boolean remove(String username);

    boolean isUsernameAlreadyInUse(String username);

    boolean isEmailAlreadyInUse(String email);

    Set<Role> getListUserRole(String username);

    UpdateDeliveryDataUser updateDataUserAboutDelivery(UpdateDeliveryDataUser updateDeliveryDataUser);

    UpdateDeliveryDataUser getDataUserAboutDelivery(String username);
}
