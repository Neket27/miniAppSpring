package app.apigatway.service.impl;

import app.apigatway.arguments.CreateUserArgument;
import app.apigatway.arguments.UpdateDataUserArgument;
import app.apigatway.dto.user.*;
import app.apigatway.entity.Role;
import app.apigatway.entity.User;
import app.apigatway.exception.ErrorException;
import app.apigatway.repository.UserRepo;
import app.apigatway.service.UserService;
import app.apigatway.utils.jwtToken.EncoderPassword;
import app.apigatway.utils.mapper.ImageMapper;
import app.apigatway.utils.mapper.UserMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
@Setter
@Getter
@Builder
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final ImageMapper imageMapper;
    private final JWTServiceImpl jwtService;

//    @Override
//    public MapReactiveUserDetailsService getUserDetailsService() {
//        return Mono.just(username -> userRepo.findByUsername(username).orElse(null));
//    }


    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.justOrEmpty(userRepo.findByUsername(username).orElse(null));
    }

    @Override
    @Transactional
    public Mono<CreateUserDto> addUser(@NonNull CreateUserArgument createUserArgument) {
        return isUsernameAlreadyInUse(createUserArgument.getUsername())
                .flatMap(usernameInUse -> {
                    if (usernameInUse) {
                        return Mono.error(new ErrorException("Пользователь с логином " + createUserArgument.getUsername() + " уже существует"));
                    }
                    CreateUserDto createUserDto = userMapper.toCreateUserDto(createUserArgument);
                    User user = User.builder()
                            .firstname(createUserDto.getFirstname())
                            .lastname(createUserDto.getLastname())
                            .username(createUserDto.getUsername())
                            .password(EncoderPassword.encode(createUserArgument.getPassword()))
                            .email(createUserDto.getEmail())
                            .roles(Collections.singleton(Role.ROLE_USER))
                            .build();

                    return Mono.just(userMapper.toCreateUserDto(userRepo.save(user)));
                });
    }

    @Override
    @Transactional
    public Mono<UpdateDataUserDto> updateDataUser(@NonNull UpdateDataUserArgument updateDataUserArgument) {
        Mono<String> username = jwtService.getUserNameFromAccessToken(updateDataUserArgument.getAccessToken());

        return username
                .flatMap(nameUser -> getByUsername(nameUser)
                        .flatMap(user -> {
                            UpdateDataUserDto updateDataUserDto = userMapper.toUpdateDataUserDto(updateDataUserArgument);
                            user.setFirstname(updateDataUserDto.getFirstname());
                            user.setLastname(updateDataUserDto.getLastname());
                            user.setEmail(updateDataUserDto.getEmail());
                            if (updateDataUserDto.getAvatar() != null)
                                user.setAvatar(imageMapper.toImage(updateDataUserDto.getAvatar()));
                            return Mono.just(userMapper.toUpdateDataUserDto(userRepo.save(user)));
                        }));
    }

    @Override
    @Transactional
    public Mono<UpdateDataUserDto> updateDataUser(UpdateUserDto updateUserDto) {
        return getByUsername(updateUserDto.getUsername())
                .flatMap(user -> {
                    user.setFirstname(updateUserDto.getFirstname());
                    user.setLastname(updateUserDto.getLastname());
                    user.setUsername(updateUserDto.getUsername());
                    user.setEmail(updateUserDto.getEmail());
                    user.setRoles(updateUserDto.getRoles());

                    if (!EncoderPassword.equalsPasswords(updateUserDto.getPassword(), user.getPassword()))
                        user.setPassword(EncoderPassword.encode(updateUserDto.getPassword()));

                    return Mono.just(userMapper.toUpdateDataUserDto(userRepo.save(user)));
                });
    }

    @Override
    @Transactional
    public Mono<Boolean> updateUserAvatar(@NonNull UpdateAvatarUserDto updateAvatarUserDto) {
        return getByUsername(updateAvatarUserDto.getUsername())
                .flatMap(user -> {
                    User userBD = userRepo.findByUsername(user.getUsername())
                            .orElseThrow(() -> new RuntimeException("Пользователь с username " + user.getUsername() + " ненайден"));
                    userBD.setAvatar(imageMapper.toImage(updateAvatarUserDto.getAvatar()));
                    userRepo.save(userBD);
                    return Mono.just(true);
                });
    }

    @Override
    @Transactional
    public Flux<UserDto> getListUsers() {
        return Flux.fromIterable(userRepo.findAll())
                .map(user -> {
                    user.setPassword(EncoderPassword.encode(user.getPassword()));
                    return userMapper.toUserDto(user);
                });
    }

    @Override
    @Transactional
    public Mono<User> getById(@NonNull Long id) {
        return Mono.justOrEmpty(userRepo.findById(id))
                .switchIfEmpty(Mono.error(new ErrorException("Пользователь с id= " + id + " не найден")));
    }

    @Override
    @Transactional
    public Mono<User> getByUsername(@NonNull String username) {
        return Mono.justOrEmpty(userRepo.findByUsername(username))
                .switchIfEmpty(Mono.error(new ErrorException("Пользователь с логином " + username + " не найден")));
    }

    @Override
    @Transactional
    public Mono<Boolean> remove(@NonNull String username) {
        return getByUsername(username)
                .flatMap(user -> {
                    userRepo.deleteById(user.getId());
                    return Mono.just(true);
                })
                .onErrorResume(ErrorException.class, e -> Mono.just(false));
    }

    @Override
    @Transactional
    public Mono<Boolean> isUsernameAlreadyInUse(@NonNull String username) {
        return Mono.just(userRepo.existsUserByUsername(username));
    }

    @Override
    @Transactional
    public Mono<Boolean> isEmailAlreadyInUse(@NonNull String email) {
        return Mono.just(userRepo.existsUserByEmail(email));
    }

    @Override
    public Mono<Set<Role>> getListUserRole(String username) {
        return Mono.justOrEmpty(userRepo.findByUsername(username)
                .map(User::getRoles)
                .orElseThrow(() -> new ErrorException("Пользователь не найден")));
    }
}
