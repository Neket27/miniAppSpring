package app.miniappspring.dto.product;

import app.miniappspring.dto.image.ImageDto;
import app.miniappspring.dto.product.category.CategoryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCardDto {
    private Long id;
    private CategoryDto category;
    private String name;
    private float cost;
    private float rating;
    private float discount;
    private List<ImageDto>imageDtoList;
}
