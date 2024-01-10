package app.miniappspring;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.UserRepo;
import app.miniappspring.service.UserService;
import app.miniappspring.service.impl.UserServiceImpl;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceMockTest {


   private UserService userService;
   private UserRepo userRepo;
   private User user;

   static long ID_USER=1;

    @BeforeEach
    void setup() {
        userRepo = Mockito.mock(UserRepo.class);
        userService =new UserServiceImpl(userRepo);
         user=User.builder()
                .id(ID_USER)
                .username("nikita")
                .password("1234")
                .roles(Set.of(Role.ROLE_USER))
                .build();
    }
    @Test
    public void testGetUserByUsername(){
        //Arrange
        User expectedUser=user;

        //Act
        when(userRepo.findByUsername(anyString())).thenReturn(Optional.ofNullable(expectedUser));
        User actualUser= userService.getByUsername("nikita");

        //Assert
        assertEquals(expectedUser,actualUser);

}

    @Test
    public void testGetUserById(){
        //Arrange
        User expectedUser =user;

        //Act
        when(userRepo.findById(anyLong())).thenReturn(Optional.ofNullable(expectedUser));
        User actualUser =userService.getById(ID_USER);

        //Assert
        assertEquals(expectedUser,actualUser);
    }


    @Test

    public void testGetUserByIdException(){
        //Arrange
        String expectedMessage = "Пользователь с id= "+ID_USER+" не найден";

        //Act
        Exception exception = assertThrows(ErrorException.class, () -> {
            userService.getById(ID_USER);
        });

        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetListUsers(){
        //Arrange
        List<User> expectedUser =List.of(user);

        //Act
        when(userRepo.findAll()).thenReturn(expectedUser);
        List<User> actualUsers=userService.getListUsers();

        //Assert
        assertEquals(expectedUser,actualUsers);

    }



}
