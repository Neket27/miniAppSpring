package app.miniappspring.dto.product;

import app.miniappspring.dto.product.category.CategoryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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
    @Schema(type = "string", format = "binary", description = "Binary data")
    byte[] image;
}
