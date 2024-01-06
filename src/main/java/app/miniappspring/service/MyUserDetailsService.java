package app.miniappspring.service;

import app.miniappspring.detail.MyUserDetails;
import app.miniappspring.dto.DtoCurrentUser;
import app.miniappspring.dto.JwtRequest;
import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import app.miniappspring.repository.UserRepo;
import app.miniappspring.utils.JwtTokenUtils;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Builder
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenUtils jwtTokenUtils;
//    private final DtoCurrentUser currentUser;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
//        if(users.isPresent())
//        currentUser.setUsername(username);
//        else
//            currentUser.setUsername("Пройдите авторизацию");
        return new MyUserDetails(user);
//        .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.ADMIN));
//        String token = jwtTokenUtils.generateToken(user);
        userRepo.save(user);
    }


    public List<User> getListUsers() {
        Iterable<User> users = userRepo.findAll();
        users.forEach(user -> user.setPassword(passwordEncoder.encode(user.getPassword())));
        return (List<User>) users;
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(()->new RuntimeException("Нет узера с id= "+id));
    }

    public User getUserByUsername(String username){
        return userRepo.findByUsername(username);
    }

//    public String createUser(JwtRequest authRequest) {
//        return addUser(User.builder()
//                .username(authRequest.getUsername())
//                .password(authRequest.getPassword())
//                .roles(Set.of(Role.ADMIN))
//                .build());
//    }
}