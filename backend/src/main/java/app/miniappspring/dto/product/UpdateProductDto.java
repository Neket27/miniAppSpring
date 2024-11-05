package app.miniappspring.dto.product;

import app.miniappspring.dto.characteristic.CharacteristicProductDto;
import app.miniappspring.dto.image.UpdateImageDto;
import app.miniappspring.entity.Category;
import app.miniappspring.entity.CharacteristicProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UpdateProductDto {
    private Long id;
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
    private CharacteristicProductDto characteristic;
    private List<UpdateImageDto> updateImageDtoList;
}
