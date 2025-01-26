package app.miniappspring.service.impl.discount;

import app.miniappspring.dto.discount.DiscountCreateDto;
import app.miniappspring.dto.discount.DiscountDto;
import app.miniappspring.entity.Discount;
import app.miniappspring.entity.Product;
import app.miniappspring.repository.DiscountRepo;
import app.miniappspring.service.DiscountService;
import app.miniappspring.service.ProductService;
import app.miniappspring.utils.mapper.DiscountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Discount discount = discountRepo.findByNameAndCity(discountCreateDto.getName(), discountCreateDto.getCity()).orElse(null);

        if (discount == null) {
            discount = discountMapper.toEntity(discountCreateDto);
            List<Product> productList = discountCreateDto.getProductIdList().stream().map(productId->productService.findProduct(productId)).toList();
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
