package app.miniappspring.action;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.arguments.UpdateUserArgument;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.dto.user.UpdateUserDto;
import app.miniappspring.entity.Image;
import app.miniappspring.entity.User;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.ImageRepo;
import app.miniappspring.service.LoadFileService;

import app.miniappspring.utils.jwtToken.mapper.UserMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class UnificationDataUser {


    private final  LoadFileService loadFileService;

    public Image unificationAvatarWithUser(MultipartFile multipartFileAvatar,User user){
        Image image = null;
        try {
            image = loadFileService.loadImage(multipartFileAvatar);

            if(image!=null)
            image.setUser(user);

        } catch (IOException e) {
            log.error(e.getMessage());

        }
        return image;
    }

}
