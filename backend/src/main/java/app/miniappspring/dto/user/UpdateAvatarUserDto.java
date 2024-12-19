package app.miniappspring.dto.user;

import app.miniappspring.dto.image.ImageDto;
import app.miniappspring.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAvatarUserDto {
    private String username;
    private ImageDto avatar;
}
