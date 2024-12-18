package app.miniappspring.controller;

import app.miniappspring.arguments.UpdateDataUserArgument;
import app.miniappspring.dto.delivey.UpdateDeliveryDataUser;
import app.miniappspring.dto.user.UpdateAvatarUserDto;
import app.miniappspring.dto.user.UpdateDataUserDto;
import app.miniappspring.entity.Role;
import app.miniappspring.service.AuthenticationService;
import app.miniappspring.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hi " + authenticationService.getAuthenticationInfo().getUsername());
    }

    //    http://localhost:8080/api/v1/user/remove/nik3
    @GetMapping("/remove/{username}")
    public ResponseEntity<String> sayHello(@PathVariable @NonNull String username) {
        return ResponseEntity.ok("Удаление пользователя прошло:  " + userService.remove(username));
    }

    @PostMapping("/data/update")
    public UpdateDataUserDto updateDataUser(@RequestBody UpdateDataUserArgument updateDataUserArgument) {
        return userService.updateDataUser(updateDataUserArgument);
    }

    @PostMapping("/data/update/delivery")
    public UpdateDeliveryDataUser updateDataUserAboutDelivery(@RequestBody UpdateDeliveryDataUser updateDeliveryDataUser) {
        return userService.updateDataUserAboutDelivery(updateDeliveryDataUser);
    }

    @GetMapping("/data/delivery")
    public UpdateDeliveryDataUser getDataUserAboutDelivery() {
        return userService.getDataUserAboutDelivery();
    }

    @PostMapping("/photo/update")
    public ResponseEntity<Boolean> updatePhotoUser(@RequestParam String username, @RequestParam MultipartFile multipartFileAvatar) {
        return ResponseEntity.ok(userService.updateUserAvatar(new UpdateAvatarUserDto(username, multipartFileAvatar)));
    }

    @GetMapping("/roles")
    public Set<Role> getListUserRole(@RequestParam String username) {
        return userService.getListUserRole(username);
    }
}
