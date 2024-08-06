package app.miniappspring.service.impl;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.arguments.UpdateDataUserArgument;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.dto.user.UpdateAvatarUserDto;
import app.miniappspring.dto.user.UpdateDataUserDto;
import app.miniappspring.dto.user.UpdateUserDto;
import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.UserRepo;
import app.miniappspring.service.UserService;
import app.miniappspring.utils.jwtToken.EncoderPassword;
import app.miniappspring.utils.jwtToken.mapper.ImageMapper;
import app.miniappspring.utils.jwtToken.mapper.UserMapper;
import app.miniappspring.utils.user.UnificationUserUtils;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Builder
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final ImageMapper imageMapper;
    private final JWTServiceImpl  jwtService;
    private final UnificationUserUtils unificationUserUtils;


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
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
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
    @Transactional(rollbackFor = Exception.class)
    public UpdateDataUserDto updateDataUser(@NonNull UpdateDataUserArgument updateDataUserArgument){

        UpdateDataUserDto updateDataUserDto = userMapper.toUpdateDataUserDto(updateDataUserArgument);
        String username = jwtService.getUserNameFromAccessToken(updateDataUserArgument.getAccessToken());
        User user = getByUsername(username);
        user = unificationUserUtils.updateUserData(user,updateDataUserDto);
        if(updateDataUserDto.getAvatar()!=null)
            user.setAvatar(imageMapper.toImage(updateDataUserDto.getAvatar()));

//           if(!EncoderPassword.equalsPasswords(UpdateDataUserDto.getPassword(),user.getPassword()) && UpdateDataUserDto.getPassword()!=null)
//           user.setPassword(EncoderPassword.encode(UpdateDataUserDto.getPassword()));
//           if(UpdateDataUserDto.getRoles()!=null)
//           user.setRoles(UpdateDataUserDto.getRoles());
           return userMapper.toUpdateDataUserDto(userRepo.save(user));
    }


    @Override
    @Transactional
    public UpdateDataUserDto updateDataUser(UpdateUserDto updateUserDto) {
        User user =getByUsername(updateUserDto.getUsername());
        user = unificationUserUtils.updateUserData(user,updateUserDto);
        user.setUsername(updateUserDto.getUsername());
        user.setRoles(updateUserDto.getRoles());

        if(!EncoderPassword.equalsPasswords(updateUserDto.getPassword(),user.getPassword()))
            user.setPassword(EncoderPassword.encode(updateUserDto.getPassword()));

        userRepo.save(user);

        return userMapper.toUpdateDataUserDto(userRepo.save(user));
    }

    @Override
    @Transactional
    public boolean updateUserAvatar(@NonNull UpdateAvatarUserDto updateAvatarUserDto){
        User user =getByUsername(updateAvatarUserDto.getUsername());
        return true;
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> getListUsers() {
        List<User> users = userRepo.findAll();
        users.forEach(user -> user.setPassword(EncoderPassword.encode(user.getPassword())));
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(@NonNull Long id) {
        return userRepo.findById(id).orElseThrow(()->new ErrorException("Пользователь с id= "+id+" не найден"));
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public boolean isUsernameAlreadyInUse(@NonNull String username) {
        return userRepo.existsUserByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmailAlreadyInUse(@NonNull String email) {
        return userRepo.existsUserByEmail(email);
    }


}