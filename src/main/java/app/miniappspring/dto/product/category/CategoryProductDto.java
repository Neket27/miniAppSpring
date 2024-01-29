package app.miniappspring.dto.product.category;

import app.miniappspring.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryProductDto {
    Category category;
    String subcategory;
    String stringValueCategory;

}
