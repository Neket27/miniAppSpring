package app.miniappspring.service.impl;

import app.miniappspring.dto.discount.DiscountCreateDto;
import app.miniappspring.dto.discount.DiscountDto;
import app.miniappspring.entity.Discount;
import app.miniappspring.entity.Product;
import app.miniappspring.repository.DiscountRepo;
import app.miniappspring.service.DiscountService;
import app.miniappspring.service.ProductService;
import app.miniappspring.utils.jwtToken.mapper.DiscountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepo discountRepo;
    private final DiscountMapper discountMapper;
    private final ProductService productService;

    @Override
    @Transactional
    public DiscountDto createDiscount(DiscountCreateDto discountCreateDto) {
        // Проверяем, существует ли скидка с данным названием и городом
        Discount discount = discountRepo.findByNameAndCity(discountCreateDto.getName(), discountCreateDto.getCity()).orElse(null);

        if (discount == null) {
            // Преобразуем DTO в сущность
            discount = discountMapper.toEntity(discountCreateDto);
            discount = discountRepo.save(discount);  // Сохраняем скидку

            // Создаем промежуточные списки для продуктов и скидок
            List<Product> productList = new ArrayList<>();

            Discount finalDiscount = discount;
            discountCreateDto.getProductIdList().forEach(productId -> {
                Product product = productService.findProduct(productId);
                product.getDiscountList().add(finalDiscount); // Добавляем скидку к продукту
                productList.add(product);  // Добавляем продукт во временный список
            });

            // Присваиваем список продуктов скидке и сохраняем
            discount.setProductList(productList);
            discountRepo.save(discount);
        }

        return discountMapper.toDiscountDto(discount);
    }



    @Override
    @Transactional
    public DiscountDto checkDiscountAtProduct(Long productId) {
        return discountMapper.toDiscountDto(discountRepo.findDiscountByProductList_Id(productId).orElse(null));
    }

    @Override
    public List<DiscountDto> getDiscountList() {
        return discountRepo.findAll().stream().map(discount -> discountMapper.toDiscountDto(discount)).toList();
    }
}
