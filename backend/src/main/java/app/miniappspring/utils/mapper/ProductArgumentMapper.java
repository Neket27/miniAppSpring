package app.miniappspring.utils.mapper;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.entity.CharacteristicProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductArgumentMapper {
    CharacteristicProduct toCharacteristicProduct(CreateProductArgument createProductArgument);

}
