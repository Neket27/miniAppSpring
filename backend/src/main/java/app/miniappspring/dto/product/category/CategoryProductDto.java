package app.miniappspring.dto.product.category;

import app.miniappspring.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryProductDto {
    private Long idProduct;
    private String nameProduct;
    private Category category;
    private String subcategory;
    private String stringValueCategory;

}
