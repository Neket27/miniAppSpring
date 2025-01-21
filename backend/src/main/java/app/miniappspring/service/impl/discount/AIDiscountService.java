package app.miniappspring.service.impl.discount;

import app.miniappspring.dto.discount.DiscountCreateDto;
import app.miniappspring.dto.discount.DiscountDto;
import app.miniappspring.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class AIDiscountService {

    private final DiscountService discountService;


    @Bean
    @Description("create Discount")
    public Function<RequestOnCreateDiscount, DiscountDto> createDiscount(){
        return requestOnCreateDiscount -> discountService.createDiscount(requestOnCreateDiscount.createDiscountDto());
    }

    @Bean
    @Description("Check a discount product by id a product")
    public Function<RequestOnCheckDiscount, DiscountDto> checkDiscountAtProduct(){
        return requestOnCheckDiscount -> discountService.checkDiscountAtProduct(requestOnCheckDiscount.idProduct());
    }

    @Bean
    @Description("Get list Discount")
    public Supplier<List<DiscountDto>> getDiscountList(){
        return ()->discountService.getDiscountList();
    }
}

record RequestOnCreateDiscount(DiscountCreateDto createDiscountDto){}
record RequestOnCheckDiscount(Long idProduct){}