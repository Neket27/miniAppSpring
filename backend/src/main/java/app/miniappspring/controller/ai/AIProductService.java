package app.miniappspring.controller.ai;

import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.service.CategoryService;
import app.miniappspring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class AIProductService {

    private final ProductService productService;
    private final CategoryService categoryService;


    public Supplier<List<ProductDetailDto>> getListProduct() {
        List<ProductDetailDto> productDetailDtoList = productService.getListProductDetail();
        productDetailDtoList.forEach(productDetailDto -> productDetailDto.getImageDtoList().clear());
        return () -> productDetailDtoList;
    }


    public List<ProductCardDto> showProductsUsingNeuralNetwork(List<Long> idProductList) {
        List<ProductCardDto> productCardDtoList = idProductList.stream().map(id -> productService.getProductCard(id)).toList();
        return productCardDtoList;
    }

//    @Bean
//    @Description("Функция для получения продуктов по категории.")
//    public Function<RequestOnGetProductsByCategory, List<ProductCardDto>> getProductsByCategory() {
//        return (c) -> productService.getProductsByCategory(new CategoryDto(c.category));


    @Bean
    @Description("Функция для получения продуктов по категории. " +
            "Функция поддерживает пагинацию, можешь указывать page и pageSize в параметре запроса.")
    public Function<RequestOnGetProductsByCategory, List<ProductCardDto>> getProductsByCategory() {
        return (c) ->{
            List<ProductCardDto> productCardDtos =   productService.getProductsByCategory(
                    new CategoryDto(c.category),
                    c.page != null ? c.page : 1, c.pageSize != null ? c.pageSize : 10
            );
            return productCardDtos.stream().map(productCardDto -> {
                productCardDto.getImageDtoList().clear();
                return productCardDto;
            }).toList();
        };
    }


}

