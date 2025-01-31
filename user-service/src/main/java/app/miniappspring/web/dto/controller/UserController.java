package app.miniappspring.web.dto.controller;

import app.miniappspring.arguments.UpdateDataUserArgument;
import app.miniappspring.web.dto.user.UpdateDataUserDto;
import app.miniappspring.entity.Role;
import app.miniappspring.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/remove/{username}")
    public ResponseEntity<String> sayHello(@PathVariable @NonNull String username) {
        return ResponseEntity.ok("Удаление пользователя прошло:  " + userService.remove(username));
    }

    @PostMapping("/data/update")
    public UpdateDataUserDto updateDataUser(@RequestBody UpdateDataUserArgument updateDataUserArgument) {
        return userService.updateDataUser(updateDataUserArgument);
    }

    @GetMapping("/roles")
    public Set<Role> getListUserRole(@RequestParam String username) {
        return userService.getListUserRole(username);
    }
}
