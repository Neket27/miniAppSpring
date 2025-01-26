package app.miniappspring.service.impl.user;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.arguments.UpdateDataUserArgument;
import app.miniappspring.dto.delivey.UpdateDeliveryDataUser;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.dto.user.UpdateDataUserDto;
import app.miniappspring.dto.user.UserDto;
import app.miniappspring.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class AIUserService {

    private final UserService userService;

    @Bean
    @Description("List users")
    public Supplier<List<UserDto>> getUserList() {
        return  ()->userService.getListUsers();
    }

    @Bean
    @Description("Create User." +
            " * Represents a user in the system with the following attributes:\n" +
            " * \n" +
            " * - firstname: The first name of the user. It should be a string containing the user's first name.\n" +
            " * - lastname: The last name of the user. It should be a string containing the user's last name.\n" +
            " * - username: A unique identifier for the user within the system. This is typically used for login purposes and should be a string.\n" +
            " * - password: The user's password for authentication. This should be a securely hashed string to ensure user security.\n" +
            " * - email: The user's email address. It should be a valid email format string used for communication and account verification.\n" +
            " * - roles: A set of roles assigned to the user. This defines the permissions and access levels the user has within the system. Each role should be represented by an instance of the Role class.\n" +
            " * \n" +
            " * Example usage:\n" +
            " * \n" +
            " * User user = new User();\n" +
            " * user.setFirstname(\"John\");\n" +
            " * user.setLastname(\"Doe\");\n" +
            " * user.setUsername(\"johndoe\");\n" +
            " * user.setPassword(\"securePassword123\");\n" +
            " */")
    public Function<RequestCreateUser,CreateUserDto> createUser() {
        return (requestCreateUser)->userService.addUser(requestCreateUser.createUserArgument());
    }

    @Bean
    @Description("Update Data User")
    public Function<RequestUpdateUserData, UpdateDataUserDto> updateDataUser() {
        return (requestUpdateUserData)-> userService.updateDataUser(requestUpdateUserData.updateDataUserArgument());
    }

    @Bean
    @Description("Deleting a user by username.")
    public Consumer<RequestDeleteUserByUsername> deleteUserByUsername(){
        return (requestDeleteUserByUsername)->userService.remove(requestDeleteUserByUsername.username());
    }

    @Bean
    @Description("Getting a list of user roles.")
    public Function<RequestGetRolesUserByUserName, Set<Role>> getListUserRoles() {
        return (requestGetRolesUserByUserName)->userService.getListUserRole(requestGetRolesUserByUserName.username());
    }

    @Bean
    @Description("Get Delivery data for the user.")
    public Function<RequestGetUserRoles, UpdateDeliveryDataUser> getDataUserAboutDelivery(){
        return (requestGetUserRoles)->userService.getDataUserAboutDelivery(requestGetUserRoles.username());
    }

}

record RequestCreateUser(CreateUserArgument createUserArgument){}
record RequestUpdateUserData(UpdateDataUserArgument updateDataUserArgument){}
record RequestDeleteUserByUsername(String username){}
record RequestGetRolesUserByUserName(String username){}
record RequestGetUserRoles(String username){}


