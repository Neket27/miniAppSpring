package app.miniappspring.utils.user;

import app.miniappspring.dto.user.UpdateDataUserDto;
import app.miniappspring.dto.user.UpdateUserDto;
import app.miniappspring.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UnificationUserUtils {

    public User updateUserData(User user, UpdateDataUserDto updateDataUserDto){
        return updateUserFirstnameAndLastnameAndEmail(user,updateDataUserDto.getFirstname(),updateDataUserDto.getLastname(),updateDataUserDto.getEmail());
    }

    public User updateUserData(User user, UpdateUserDto updateUserDto){
        return updateUserFirstnameAndLastnameAndEmail(user,updateUserDto.getFirstname(),updateUserDto.getLastname(),updateUserDto.getEmail());
    }

    private User updateUserFirstnameAndLastnameAndEmail(User user, String firstname, String lastname, String email){
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        return user;
    }
}
