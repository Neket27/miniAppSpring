package app.miniappspring.service;

import app.miniappspring.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LoadFileService {
    Image loadImage(MultipartFile multipartFile) throws IOException;
}
