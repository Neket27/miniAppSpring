package app.miniappspring.utils.mapper;

import app.miniappspring.dto.characteristic.CharacteristicProductDto;
import app.miniappspring.dto.product.UpdateProductDto;
import app.miniappspring.entity.CharacteristicProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacteristicMapper {
    CharacteristicProductDto toCharacteristicProductDto(CharacteristicProduct characteristicProduct);
    CharacteristicProduct toCharacteristicProduct(CharacteristicProductDto characteristicProductDto);
}

