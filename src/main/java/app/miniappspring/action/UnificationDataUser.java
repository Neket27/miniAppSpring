package app.miniappspring.action;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.entity.Image;
import app.miniappspring.entity.User;
import app.miniappspring.service.LoadFileService;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
//@Transactional
@RequiredArgsConstructor
public class UnificationDataUser {
    private final LoadFileService loadFileService;
//    private final UserMapper userMapper;

    public User unificationDataImageWithUser(CreateUserArgument createUserArgument){
        User user=User.builder().build();
        try {
         Image image = loadFileService.loadImage(createUserArgument.getAvatarMultipartFile());
//         user = userMapper.toEntity(createUserArgument);
        user.setAvatar(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

}
