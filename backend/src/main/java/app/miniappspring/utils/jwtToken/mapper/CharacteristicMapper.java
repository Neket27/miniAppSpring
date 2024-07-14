package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.dto.characteristic.CharacteristicProductDto;
import app.miniappspring.entity.CharacteristicProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacteristicMapper {
    CharacteristicProductDto toCharacteristicProductDto(CharacteristicProduct characteristicProduct);
}
