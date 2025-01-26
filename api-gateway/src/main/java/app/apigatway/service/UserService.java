package app.apigatway.service;


import app.apigatway.arguments.CreateUserArgument;
import app.apigatway.arguments.UpdateDataUserArgument;
import app.apigatway.dto.user.*;
import app.apigatway.entity.Role;
import app.apigatway.entity.User;
import lombok.NonNull;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface UserService extends ReactiveUserDetailsService {

    //    MapReactiveUserDetailsService getUserDetailsService();
    Mono<UserDetails> findByUsername(String username);

    Mono<CreateUserDto> addUser(CreateUserArgument createUserArgument);

    Mono<UpdateDataUserDto> updateDataUser(@NonNull UpdateDataUserArgument updateDataUserArgument);

    Mono<UpdateDataUserDto> updateDataUser(UpdateUserDto updateUserDto);

    Mono<Boolean> updateUserAvatar(UpdateAvatarUserDto updateAvatarUserDto);

    Flux<UserDto> getListUsers();

    Mono<User> getById(Long id);

    Mono<User> getByUsername(String username);

    Mono<Boolean> remove(String username);

    Mono<Boolean> isUsernameAlreadyInUse(String username);

    Mono<Boolean> isEmailAlreadyInUse(String email);

    Mono<Set<Role>> getListUserRole(String username);
}

