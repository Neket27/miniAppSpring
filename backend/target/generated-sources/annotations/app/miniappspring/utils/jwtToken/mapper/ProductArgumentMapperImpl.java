package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.entity.CharacteristicProduct;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-14T16:03:18+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ProductArgumentMapperImpl implements ProductArgumentMapper {

    @Override
    public CharacteristicProduct toCharacteristicProduct(CreateProductArgument createProductArgument) {
        if ( createProductArgument == null ) {
            return null;
        }

        CharacteristicProduct.CharacteristicProductBuilder characteristicProduct = CharacteristicProduct.builder();

        characteristicProduct.producerCountry( createProductArgument.getProducerCountry() );
        characteristicProduct.sellerWarranty( createProductArgument.getSellerWarranty() );

        return characteristicProduct.build();
    }
}
