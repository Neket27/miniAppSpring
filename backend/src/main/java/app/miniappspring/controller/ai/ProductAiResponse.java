package app.miniappspring.controller.ai;

import app.miniappspring.dto.product.ProductCardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductAiResponse {
    private String message;
    private List<ProductCardDto> recommendedProducts;
}
