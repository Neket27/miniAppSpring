package app.miniappspring.utils.mapper;

import app.miniappspring.dto.product.category.SearchProductDto;
import app.miniappspring.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SearchMapper {

    public SearchProductDto toSearchProductDto(Product product){
        return SearchProductDto.builder()
                .idProduct(product.getId())
                .nameProduct(product.getName())
                .category(product.getCategoryItem().getName())
                .build();
    }
}
