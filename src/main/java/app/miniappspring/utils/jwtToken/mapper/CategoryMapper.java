package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.dto.product.category.CategoryProductDto;
import app.miniappspring.entity.CategoryProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper{

    @Mapping(source = "category.russianValue", target="stringValueCategory")
    CategoryProduct toCategoryProduct(CreateProductArgument createProductArgument);

    CategoryProductDto toCategoryProductDto(CategoryProduct categoryProduct);
}
