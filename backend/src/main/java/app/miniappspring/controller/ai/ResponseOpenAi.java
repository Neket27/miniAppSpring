package app.miniappspring.controller.ai;

import app.miniappspring.dto.product.ProductCardDto;

import java.util.List;

public record ResponseOpenAi(
        String message,
        List<ProductCardDto> productCards
) {
}
