package app.miniappspring.arguments;

import app.miniappspring.dto.image.CreateImageDto;
import app.miniappspring.entity.Category;
import app.miniappspring.entity.CategoryProduct;
import app.miniappspring.entity.CharacteristicProduct;
import app.miniappspring.entity.Image;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductArgument {
    private Category category;
    private String subcategory;
    private String name;
    private float cost;
    private float rating;
    private String description;
    private String brand;
    private String article;
    private boolean available;
    private int stock;
    private String detail;
    private String producerCountry;
    private int sellerWarranty;
    private List<CreateImageDto> createImageDtoList;

}
