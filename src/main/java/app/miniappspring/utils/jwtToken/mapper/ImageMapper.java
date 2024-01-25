package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.entity.Image;
import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image toImage(MultipartFile multipartFile) throws IOException;


    default byte[] toBytes(String string) {
        return string != null ? string.getBytes() : null;
    }
}
