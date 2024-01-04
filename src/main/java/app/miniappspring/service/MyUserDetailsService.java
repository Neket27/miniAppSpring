package app.miniappspring.service;

import app.miniappspring.detail.MyUserDetails;
import app.miniappspring.entity.User;
import app.miniappspring.repository.UserRepo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor
@Builder
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> users = userRepo.findByUsername(username);
        return users.map(MyUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    public List<User> getUsersByName(String username){
        return userRepo.findByUsername(username).stream().toList();
    }

}