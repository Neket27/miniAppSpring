package app.miniappspring.service.impl;


import app.miniappspring.action.UnificationDataUser;
import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.UserRepo;
import app.miniappspring.service.LoadFileService;
import app.miniappspring.service.UserService;
import app.miniappspring.utils.jwtToken.EncoderPassword;
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
//    private final UnificationDataUser unificationDataUser;

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
    public void addUser(@NonNull CreateUserArgument createUserArgument) {
        if( isUsernameAlreadyInUse(createUserArgument.getUsername()))
            throw new ErrorException("Пользователь с логином "+createUserArgument.getUsername()+" уже существует");
//          User user=  unificationDataUser.unificationDataImageWithUser(createUserArgument);
       User user =User.builder()
               .username("lol")
               .password("lol")
               .build();
        user.setPassword(EncoderPassword.encode(createUserArgument.getPassword()));
        user.setRoles(Collections.singleton(Role.ROLE_USER));
        userRepo.save(user);
    }

    @Override
    public List<User> getListUsers() {
        Iterable<User> users = userRepo.findAll();
        users.forEach(user -> user.setPassword(EncoderPassword.encode(user.getPassword())));
        return (List<User>) users;
    }

    @Override
    public User getById(@NonNull Long id) {
        return userRepo.findById(id).orElseThrow(()->new ErrorException("Пользователь с id= "+id+" не найден"));
    }

    @Override
    public User getByUsername(@NonNull String username){
        return userRepo.findByUsername(username).orElseThrow(()->new ErrorException("Пользователь с логином "+username+" не найден"));
    }

    @Override
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

    @Transactional
    @Override
    public boolean isUsernameAlreadyInUse(@NonNull String username) {
        return userRepo.existsUserByUsername(username);
    }

    @Transactional
    @Override
    public boolean isEmailAlreadyInUse(@NonNull String email) {
        return userRepo.existsUserByEmail(email);
    }

}