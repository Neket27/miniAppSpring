package app.miniappspring.service.impl;

import app.miniappspring.entity.Image;
import app.miniappspring.service.LoadFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static app.miniappspring.controller.GreetingController.UPLOAD_DIRECTORY;

@Service
public class LoadFileServiceImpl implements LoadFileService {
    @Override
    public Image loadImage(MultipartFile multipartFile) throws IOException {
        Image image;
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, multipartFile.getOriginalFilename());
       try{
           image = Image.builder()
                   .bytes(multipartFile.getBytes())
                   .build();

           Files.write(fileNameAndPath, multipartFile.getBytes());
       }catch (Exception e){throw e;}

    return image;
    }
}
