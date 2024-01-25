package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.entity.CharacteristicProduct;
import app.miniappspring.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductArgumentMapper {
    CharacteristicProduct toCharacteristicProduct(CreateProductArgument createProductArgument);
}
