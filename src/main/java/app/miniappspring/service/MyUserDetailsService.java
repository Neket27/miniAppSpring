package app.miniappspring.service;


import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.UserRepo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class MyUserDetailsService  {
    @Autowired
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


//    @Override
//    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByUsername(username).orElseThrow(()->new ErrorException("Пользователь с логином "+username+" не найден"));
//        return new MyUserDetails(user);
//    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
    }


    public List<User> getListUsers() {
        Iterable<User> users = userRepo.findAll();
        users.forEach(user -> user.setPassword(passwordEncoder.encode(user.getPassword())));
        return (List<User>) users;
    }

    public User getById(Long id) {
        return userRepo.findById(id).orElseThrow(()->new ErrorException("Пользователь с id= "+id+" не найден"));
    }

    public User getByUsername(String username){
        return userRepo.findByUsername(username).orElseThrow(()->new ErrorException("Пользователь с логином "+username+" не найден"));
    }

//    public UserDetailsService userDetailsService() {
//        User user = userRepo.findByUsername(username).orElseThrow(()->new ErrorException("Пользователь с логином "+username+" не найден"));
//        return loadUserByUsername(user.getUsername());
//    }

//    public UserDetailsService userDetailsService() {
//        return UserDetailsService.
//    }


}