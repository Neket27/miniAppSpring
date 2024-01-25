package app.miniappspring.service.impl;


import app.miniappspring.action.UnificationDataUser;
import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.arguments.UpdateUserArgument;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.dto.user.UpdateAvatarUserDto;
import app.miniappspring.dto.user.UpdateUserDto;
import app.miniappspring.entity.ImageUser;
import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.ImageRepo;
import app.miniappspring.repository.UserRepo;
import app.miniappspring.service.UserService;
import app.miniappspring.utils.jwtToken.EncoderPassword;
import app.miniappspring.utils.jwtToken.mapper.UserMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Setter
@Getter
@Builder
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ImageRepo imageRepo;
    private final UnificationDataUser unificationDataUser;
    private final UserMapper userMapper;


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
               return userRepo.findByUsername(username).orElseThrow(()->new ErrorException("Пользователь с логином "+username+" не найден"));
            }
        };
    }


    @Override
    @Transactional
    public CreateUserDto addUser(@NonNull CreateUserArgument createUserArgument) {
        if( isUsernameAlreadyInUse(createUserArgument.getUsername()))
            throw new ErrorException("Пользователь с логином "+createUserArgument.getUsername()+" уже существует");
        CreateUserDto createUserDto =userMapper.toCreateUserDto(createUserArgument);
        User user = User.builder()
                .firstname(createUserDto.getFirstname())
                .lastname(createUserDto.getLastname())
                .username(createUserDto.getUsername())
                .password(EncoderPassword.encode(createUserArgument.getPassword()))
                .email(createUserDto.getEmail())
                .roles(Collections.singleton(Role.ROLE_USER))
                .build();

            return userMapper.toCreateUserDto(userRepo.save(user));
    }

    @Override
    @Transactional
    public UpdateUserDto updateDataUser(@NonNull UpdateUserArgument updateUserArgument){
        UpdateUserDto updateUserDto = userMapper.toUpdateUserDto(updateUserArgument);
           User user =getByUsername(updateUserDto.getUsername());
           user.setFirstname(updateUserDto.getFirstname());
           user.setLastname(updateUserDto.getLastname());
           user.setUsername(updateUserDto.getUsername());
           if(!EncoderPassword.equalsPasswords(updateUserDto.getPassword(),user.getPassword()) && updateUserDto.getPassword()!=null)
           user.setPassword(EncoderPassword.encode(updateUserDto.getPassword()));
           user.setEmail(updateUserDto.getEmail());
           if(updateUserDto.getRoles()!=null)
           user.setRoles(updateUserDto.getRoles());
           return userMapper.toUpdateUserDto(userRepo.save(user));
    }

    @Override
    @Transactional
    public UpdateUserDto updateDataUser(UpdateUserDto updateUserDto){
        User user =getByUsername(updateUserDto.getUsername());
        user.setFirstname(updateUserDto.getFirstname());
        user.setLastname(updateUserDto.getLastname());
        user.setUsername(updateUserDto.getUsername());
        if(!EncoderPassword.equalsPasswords(updateUserDto.getPassword(),user.getPassword()))
            user.setPassword(EncoderPassword.encode(updateUserDto.getPassword()));
        user.setEmail(updateUserDto.getEmail());
            user.setRoles(updateUserDto.getRoles());
        return userMapper.toUpdateUserDto(userRepo.save(user));
    }

    @Override
    @Transactional
    public boolean updateUserAvatar(@NonNull UpdateAvatarUserDto updateAvatarUserDto){
        User user =getByUsername(updateAvatarUserDto.getUsername());
        imageRepo.deleteById(user.getId());
        ImageUser avatar = unificationDataUser.unificationAvatarWithUser(updateAvatarUserDto.getMultipartFileAvatar(),user);
        imageRepo.save(avatar);
        return true;
    }


    @Override
    @Transactional
    public List<User> getListUsers() {
        Iterable<User> users = userRepo.findAll();
        users.forEach(user -> user.setPassword(EncoderPassword.encode(user.getPassword())));
        return (List<User>) users;
    }

    @Override
    @Transactional
    public User getById(@NonNull Long id) {
        return userRepo.findById(id).orElseThrow(()->new ErrorException("Пользователь с id= "+id+" не найден"));
    }

    @Override
    @Transactional
    public User getByUsername(@NonNull String username){
        return userRepo.findByUsername(username).orElseThrow(()->new ErrorException("Пользователь с логином "+username+" не найден"));
    }

    @Override
    @Transactional
    public boolean remove(@NonNull String username) {
        try {
           Long id= getByUsername(username).getId();
            userRepo.deleteById(id);
            return true;
        }catch (ErrorException e){
            log.error("Ошибка удаления пользователя: ",e);
            return false;
        }
    }

    @Override
    @Transactional
    public boolean isUsernameAlreadyInUse(@NonNull String username) {
        return userRepo.existsUserByUsername(username);
    }

    @Override
    @Transactional
    public boolean isEmailAlreadyInUse(@NonNull String email) {
        return userRepo.existsUserByEmail(email);
    }

}