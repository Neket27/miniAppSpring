package app.miniappspring.dto.product;

import app.miniappspring.entity.Category;
import app.miniappspring.entity.CategoryProduct;
import app.miniappspring.entity.CharacteristicProduct;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {
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
    private CharacteristicProduct characteristic;
    private byte[] image;
}
