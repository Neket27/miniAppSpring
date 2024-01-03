package app.miniappspring.service;

import app.miniappspring.detail.MyUserDetails;
import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import app.miniappspring.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private  UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> users = userRepo.findByUsername(username);
        return users.map(MyUserDetails::new).orElseThrow(()->new UsernameNotFoundException(username+ " not found"));
    }

    public void addUser(User user){
       // User user =new User(1L,"a","a");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }


}
