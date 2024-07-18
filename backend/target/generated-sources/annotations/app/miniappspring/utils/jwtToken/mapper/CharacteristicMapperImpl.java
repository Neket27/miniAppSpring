package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.dto.characteristic.CharacteristicProductDto;
import app.miniappspring.entity.CharacteristicProduct;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-18T13:45:47+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class CharacteristicMapperImpl implements CharacteristicMapper {

    @Override
    public CharacteristicProductDto toCharacteristicProductDto(CharacteristicProduct characteristicProduct) {
        if ( characteristicProduct == null ) {
            return null;
        }

        CharacteristicProductDto.CharacteristicProductDtoBuilder characteristicProductDto = CharacteristicProductDto.builder();

        characteristicProductDto.id( characteristicProduct.getId() );
        characteristicProductDto.producerCountry( characteristicProduct.getProducerCountry() );
        characteristicProductDto.sellerWarranty( characteristicProduct.getSellerWarranty() );

        return characteristicProductDto.build();
    }
}
