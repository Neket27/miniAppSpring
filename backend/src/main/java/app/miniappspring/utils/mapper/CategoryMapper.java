package app.miniappspring.utils.mapper;

import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);
}
