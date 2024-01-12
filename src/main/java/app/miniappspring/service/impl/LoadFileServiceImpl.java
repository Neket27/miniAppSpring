package app.miniappspring.service.impl;

import app.miniappspring.entity.Image;
import app.miniappspring.service.LoadFileService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.Generated;
import org.springframework.aot.generate.GeneratedClass;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.random.RandomGenerator;

import static app.miniappspring.controller.GreetingController.UPLOAD_DIRECTORY;

@Service
public class LoadFileServiceImpl implements LoadFileService {
    @Override
    public Image loadImage(MultipartFile multipartFile) throws IOException {
        if (multipartFile != null) {
            Image image;
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, multipartFile.getOriginalFilename());
            try {
                Long id = RandomGenerator.getDefault().nextLong();
                image = Image.builder()
                        .id(id)
                        .name(multipartFile.getName())
                        .originalFileName(multipartFile.getOriginalFilename())
                        .size(multipartFile.getSize())
                        .contentType(multipartFile.getContentType())
                        .bytes(multipartFile.getBytes())
                        .build();

                Files.write(fileNameAndPath, multipartFile.getBytes());
            } catch (Exception e) {
                throw e;
            }

            return image;
        }
        return null;
    }
}
