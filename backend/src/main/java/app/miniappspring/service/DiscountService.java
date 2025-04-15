package app.miniappspring.service;

import app.miniappspring.dto.discount.DiscountCreateDto;
import app.miniappspring.dto.discount.DiscountDto;
import app.miniappspring.dto.product.ProductCardDto;

import java.util.List;

public interface DiscountService {
   DiscountDto createDiscount(DiscountCreateDto discountCreateDto);
   DiscountDto checkDiscountAtProduct(Long productId);

   List<DiscountDto> getDiscountList();

   List<ProductCardDto> getProductsWithDiscountByTown(String town);
}
