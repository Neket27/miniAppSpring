package app.miniappspring.dto.product.category;

import app.miniappspring.entity.CategoryProduct;
import lombok.*;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDto {
   private Map<String,Integer> numberOfProductsInThisCategory;

}
