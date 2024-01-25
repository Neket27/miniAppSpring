package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.dto.product.CreateProductDto;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProductArgumentMapper.class)
public interface ProductMapper {

    ProductCardDto toProductCardDto(Product product);
    Product toProductCard(ProductCardDto productCardDto);
    Product toProductCard(CreateProductDto createProductDto);
    ProductDetailDto toProductDetailDto(Product product);

}
