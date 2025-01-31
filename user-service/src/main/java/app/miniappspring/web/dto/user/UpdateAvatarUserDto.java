package app.miniappspring.web.dto.user;

import app.miniappspring.web.dto.image.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAvatarUserDto {
    private String username;
    private ImageDto avatar;
}
