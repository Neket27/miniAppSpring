package app.miniappspring.dto.product;

import app.miniappspring.dto.characteristic.CharacteristicProductDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.dto.image.ImageDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class ProductDetailDto {
    private Long id;
    private String category;
    private String name;
    private Float cost;
    private String description;
    private String brand;
    private String note;
    private boolean available;
    private int stock;
    private CharacteristicProductDto characteristicProductDto;
    private String detail;
    private List<FeedbackDto> feedbackDtoList;
    private List<ImageDto> imageDtoList;
}
