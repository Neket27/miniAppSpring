package app.miniappspring.utils.mapper;

import app.miniappspring.arguments.CreateProductArgument;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T16:17:56+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class ProductArgumentMapperImpl implements ProductArgumentMapper {

    @Override
    public CharacteristicProduct toCharacteristicProduct(CreateProductArgument createProductArgument) {
        if ( createProductArgument == null ) {
            return null;
        }

        CharacteristicProduct characteristicProduct = new CharacteristicProduct();

        characteristicProduct.setProducerCountry( createProductArgument.getProducerCountry() );
        characteristicProduct.setSellerWarranty( createProductArgument.getSellerWarranty() );

        return characteristicProduct;
    }
}
