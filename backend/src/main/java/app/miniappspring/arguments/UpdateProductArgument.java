package app.miniappspring.arguments;

import app.miniappspring.dto.image.CreateImageDto;
import app.miniappspring.dto.image.UpdateImageDto;
import app.miniappspring.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateProductArgument {
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
    private String producerCountry;
    private int sellerWarranty;
    private List<UpdateImageDto> updateImageDtoList;
}
