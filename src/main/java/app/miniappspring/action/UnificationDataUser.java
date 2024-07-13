//package app.miniappspring.action;
//
//import app.miniappspring.entity.User;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@Slf4j
//@Component
//@Transactional
//@RequiredArgsConstructor
//public class UnificationDataUser {
//
//
//    private final  LoadFileService loadFileService;
//
//    public ImageUser unificationAvatarWithUser(MultipartFile multipartFileAvatar, User user){
//        ImageUser image = null;
//        try {
//            image = loadFileService.loadImage(multipartFileAvatar);
//
//            if(image!=null)
//            image.setUser(user);
//
//        } catch (IOException e) {
//            log.error(e.getMessage());
//
//        }
//        return image;
//    }
//
//}
