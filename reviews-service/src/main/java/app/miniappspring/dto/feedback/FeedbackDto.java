package app.miniappspring.dto.feedback;

import app.miniappspring.dto.image.ImageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class FeedbackDto {
    private Long id;
    private String nameUser;
    private String email;
    private ImageDto photoUser;
    private String message;
    private float evaluation;
    private List<byte[]> imageList;
    private Date date;
}

