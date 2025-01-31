package app.miniappspring.dto.feedback;

import app.miniappspring.dto.image.CreateImageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeedbackCreateDto {
    private Long productId;
    private String nameUser;
    private String email;
    private String message;
    private float evaluation;
    private List<CreateImageDto> imageList;
    private Long UserId;

}
