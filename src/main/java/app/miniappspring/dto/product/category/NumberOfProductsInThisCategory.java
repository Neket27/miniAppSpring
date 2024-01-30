package app.miniappspring.dto.product.category;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NumberOfProductsInThisCategory {
   private Map<String,Integer> numberOfProductsInThisCategory;

}
