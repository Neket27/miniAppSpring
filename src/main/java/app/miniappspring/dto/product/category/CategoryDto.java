package app.miniappspring.dto.product.category;

import app.miniappspring.entity.CategoryProduct;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDto {
   private List<CategoryProduct> categoryProducts;
}
