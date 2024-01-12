package app.miniappspring.action;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.entity.Image;
import app.miniappspring.entity.User;
import app.miniappspring.service.LoadFileService;

import app.miniappspring.utils.jwtToken.mapper.UserMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
//@Transactional
@RequiredArgsConstructor
public class UnificationDataUser {

    private final UserMapper userMapper;
    private final  LoadFileService loadFileService;
    public CreateUserDto unificationDataFileWithCreateUserDto(CreateUserArgument createUserArgument){
        CreateUserDto createUserDto;
        try {
            Image image = loadFileService.loadImage(createUserArgument.getAvatarMultipartFile());
            createUserDto = userMapper.toCreateUserDto(createUserArgument);
            if(image!=null)
            createUserDto.setAvatar(image);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return createUserDto;
    }

}
