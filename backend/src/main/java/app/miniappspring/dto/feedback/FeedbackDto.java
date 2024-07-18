package app.miniappspring.dto.feedback;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class FeedbackDto {
    private Long id;
    private Long idProduct;
    private String nameUser;
    private String email;
    private String message;
    private float evaluation;
    private List<byte[]> imageList;

}
