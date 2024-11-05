package app.miniappspring.service.impl;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.arguments.UpdateDataUserArgument;
import app.miniappspring.dto.delivey.UpdateDeliveryDataUser;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.dto.user.UpdateAvatarUserDto;
import app.miniappspring.dto.user.UpdateDataUserDto;
import app.miniappspring.dto.user.UpdateUserDto;
import app.miniappspring.entity.DeliveryDataUser;
import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.DeliveryDataUserRepo;
import app.miniappspring.repository.UserRepo;
import app.miniappspring.service.UserService;
import app.miniappspring.utils.jwtToken.EncoderPassword;
import app.miniappspring.utils.jwtToken.mapper.DeliveryMapper;
import app.miniappspring.utils.jwtToken.mapper.ImageMapper;
import app.miniappspring.utils.jwtToken.mapper.UserMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Collections;
import java.util.List;
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
    private final JWTServiceImpl  jwtService;
    private final DeliveryDataUserRepo deliveryDataUserRepo;
    private final DeliveryMapper deliveryMapper;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                    return userRepo.findByUsername(username).orElseThrow(()->new ErrorException("Пользователь с логином "+username+" не найден"));
                    return userRepo.findByUsername(username).orElse(null);

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
    public UpdateDataUserDto updateDataUser(@NonNull UpdateDataUserArgument updateDataUserArgument){

        UpdateDataUserDto updateDataUserDto = userMapper.toUpdateDataUserDto(updateDataUserArgument);
        String username = jwtService.getUserNameFromAccessToken(updateDataUserArgument.getAccessToken());

        User user = getByUsername(username);
        user.setFirstname(updateDataUserDto.getFirstname());
        user.setLastname(updateDataUserDto.getLastname());
        user.setEmail(updateDataUserDto.getEmail());
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
    public UpdateDataUserDto updateDataUser(UpdateDataUserDto updateDataUserDto){
//        User user =getByUsername(UpdateDataUserDto.getUsername());
//        user.setFirstname(UpdateDataUserDto.getFirstname());
//        user.setLastname(UpdateDataUserDto.getLastname());
//        user.setUsername(UpdateDataUserDto.getUsername());
//        if(!EncoderPassword.equalsPasswords(UpdateDataUserDto.getPassword(),user.getPassword()))
//            user.setPassword(EncoderPassword.encode(UpdateDataUserDto.getPassword()));
//        user.setEmail(UpdateDataUserDto.getEmail());
//            user.setRoles(UpdateDataUserDto.getRoles());
        User user = User.builder()
                .firstname(updateDataUserDto.getFirstname())
                .lastname(updateDataUserDto.getLastname())
                .email(updateDataUserDto.getEmail())
                .avatar(imageMapper.toImage(updateDataUserDto.getAvatar()))
                .build();
        return userMapper.toUpdateDataUserDto(userRepo.save(user));
    }

    @Override
    @Transactional
    public UpdateDataUserDto updateDataUser(UpdateUserDto updateUserDto) {
        User user =getByUsername(updateUserDto.getUsername());
        user.setFirstname(updateUserDto.getFirstname());
        user.setLastname(updateUserDto.getLastname());
        user.setUsername(updateUserDto.getUsername());
        user.setEmail(updateUserDto.getEmail());
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
//        imageRepo.deleteById(user.getId());

//        user.setAvatar(imageMapper.toImage(u));

//        imageRepo.save(avatar);
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
        try {
            return userRepo.findByUsername(username).orElseThrow(()->new ErrorException("Пользователь с логином "+username+" не найден"));
        }catch (Exception e){
            return null;
        }
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

    @Override
    public Set<Role> getListUserRole(String username) {
        return userRepo.findByUsername(username).orElseThrow(()->new ErrorException("Пользоватьль не найден")).getRoles();
    }

    @Override
    @Transactional
    public UpdateDeliveryDataUser updateDataUserAboutDelivery(UpdateDeliveryDataUser updateDeliveryDataUser) {
        String username = (String) RequestContextHolder.currentRequestAttributes().getAttribute("username", RequestAttributes.SCOPE_REQUEST);
        if(updateDeliveryDataUser.getId()!=null) {
            DeliveryDataUser deliveryDataUser = deliveryDataUserRepo.findById(updateDeliveryDataUser.getId()).orElseThrow(() -> new RuntimeException("Данные пользователя о доставки с id=" + updateDeliveryDataUser.getId() + " ненайдены"));
            deliveryDataUser = deliveryMapper.updateData(deliveryDataUser, updateDeliveryDataUser);
            deliveryDataUser.setUser(getByUsername(username));
            deliveryDataUserRepo.save(deliveryDataUser);
        }else {
            DeliveryDataUser deliveryDataUser =deliveryMapper.toEntity(updateDeliveryDataUser);
            deliveryDataUser.setUser(getByUsername(username));
            deliveryDataUserRepo.save(deliveryDataUser);
        }
            return updateDeliveryDataUser;
    }

    @Override
    public UpdateDeliveryDataUser getDataUserAboutDelivery() {
        String username = (String) RequestContextHolder.currentRequestAttributes().getAttribute("username",RequestAttributes.SCOPE_REQUEST);
        DeliveryDataUser deliveryDataUser =deliveryDataUserRepo.findByUser_Username(username).orElse(null);
        if(deliveryDataUser==null)
            return null;

        return deliveryMapper.toUpdateDto(deliveryDataUser);
    }

}