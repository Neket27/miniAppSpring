package app.miniappspring.controller;

import app.miniappspring.service.AuthenticationService;
import app.miniappspring.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi "+authenticationService.getAuthenticationInfo().getUsername());
    }

//    http://localhost:8080/api/v1/user/remove/nik3
    @GetMapping("/remove/{username}")
    public ResponseEntity<String> sayHello(@PathVariable String username){
        return ResponseEntity.ok("Удаление пользователя прошло:  "+userService.remove(username));
    }
}
