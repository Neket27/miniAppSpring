package app.miniappspring.utils.mapper;

import app.miniappspring.dto.characteristic.CharacteristicProductDto;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T16:17:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
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

    @Override
    public CharacteristicProduct toCharacteristicProduct(CharacteristicProductDto characteristicProductDto) {
        if ( characteristicProductDto == null ) {
            return null;
        }

        CharacteristicProduct characteristicProduct = new CharacteristicProduct();

        characteristicProduct.setId( characteristicProductDto.getId() );
        characteristicProduct.setProducerCountry( characteristicProductDto.getProducerCountry() );
        characteristicProduct.setSellerWarranty( characteristicProductDto.getSellerWarranty() );

        return characteristicProduct;
    }
}
