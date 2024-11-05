package app.miniappspring.service;

import app.miniappspring.dto.discount.DiscountCreateDto;
import app.miniappspring.dto.discount.DiscountDto;

import java.util.List;

public interface DiscountService {
   DiscountDto createDiscount(DiscountCreateDto discountCreateDto);
   DiscountDto checkDiscountAtProduct(Long productId);

   List<DiscountDto> getDiscountList();
}
