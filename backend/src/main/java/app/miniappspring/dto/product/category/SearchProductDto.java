package app.miniappspring.dto.product.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchProductDto {
    private Long idProduct;
    private String nameProduct;
    private String category;
}
