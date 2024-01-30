package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.dto.product.category.CategoryProductDto;
import app.miniappspring.entity.CategoryProduct;
import app.miniappspring.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoryMapper{

    @Mapping(source = "category.russianValue", target="stringValueCategory")
    CategoryProduct toCategoryProduct(CreateProductArgument createProductArgument);
    @Mapping(source = "category",target = "categoryProduct")
    @Mapping(source = "subcategory",target = "subcategory")
    CategoryDto toCategoryDto(CategoryProduct categoryProduct);
    CategoryProductDto toCategoryProductDto(CategoryProduct categoryProduct);

    @Mapping(source = "id", target = "idProduct")
    @Mapping(source = "name",target = "nameProduct")
    @Mapping(source = "categoryProduct.category", target = "category")
    @Mapping(source = "categoryProduct.subcategory", target = "subcategory")
    @Mapping(source = "categoryProduct.stringValueCategory", target = "stringValueCategory")
    CategoryProductDto toCategoryProduct(Product product);

    default List<CategoryProductDto> toListCategoryProductDto(Set<CategoryProduct> categoryProductSet){
        return categoryProductSet.stream()
                .map(this::toCategoryProductDto)
                .collect(Collectors.toList());
    }

    default List<CategoryProductDto> toListCategoryProductDto(List<Product> productList) {
        return productList.stream()
                .map(this::toCategoryProduct)
                .collect(Collectors.toList());
    }

}
