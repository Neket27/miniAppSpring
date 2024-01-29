package app.miniappspring.arguments;

import app.miniappspring.entity.Category;
import app.miniappspring.entity.CategoryProduct;
import app.miniappspring.entity.CharacteristicProduct;
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
//    @Schema(type = "string", format = "binary", description = "Binary data")
    private byte[] image;
    private String producerCountry;
    private int sellerWarranty;
//    @ArraySchema(schema = @Schema(type = "string", format = "binary"))
    private List<byte[]> images;

}
