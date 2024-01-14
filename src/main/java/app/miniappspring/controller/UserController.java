package app.miniappspring.controller;

import app.miniappspring.arguments.UpdateUserArgument;
import app.miniappspring.dto.user.UpdateAvatarUserDto;
import app.miniappspring.dto.user.UpdateUserDto;
import app.miniappspring.service.AuthenticationService;
import app.miniappspring.service.UserService;
import jakarta.servlet.MultipartConfigElement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;


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
    public ResponseEntity<String> sayHello(@PathVariable @NonNull String username){
        return ResponseEntity.ok("Удаление пользователя прошло:  "+userService.remove(username));
    }

    @PostMapping(path = "/updateDataUser")
    public ResponseEntity<UpdateUserDto> updateDataUser(@RequestBody UpdateUserArgument updateUserArgument){
        return ResponseEntity.ok(userService.updateDataUser(updateUserArgument));
    }

    @PostMapping("/updatePhotoUser")
    public ResponseEntity<Boolean> updatePhotoUser(@RequestParam String username,@RequestParam MultipartFile multipartFileAvatar){
       return ResponseEntity.ok(userService.updateUserAvatar(new UpdateAvatarUserDto(username,multipartFileAvatar)));
    }


}
