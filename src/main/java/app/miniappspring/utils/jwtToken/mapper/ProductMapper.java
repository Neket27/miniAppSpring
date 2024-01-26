package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.dto.product.CreateProductDto;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.entity.CharacteristicProduct;
import app.miniappspring.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = ProductArgumentMapper.class)
public interface ProductMapper {

    ProductCardDto toProductCardDto(Product product);
    Product toProduct(ProductCardDto productCardDto);
    Product toProduct(CreateProductDto createProductDto);
    ProductDetailDto toProductDetailDto(Product product);

    Product toProduct(CreateProductArgument createProductArgument);

    CreateProductDto toCreateProductDto(CreateProductArgument createProductArgument);

}
