package app.miniappspring.controller.ai;

import app.miniappspring.dto.product.ProductCardDto;
import lombok.Builder;

import java.util.List;

@Builder
public record ResponseOpenAi(
        String message,
        List<ProductCardDto> productCards
) {
}
