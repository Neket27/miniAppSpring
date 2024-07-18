package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.dto.cart.CreateProductCartDto;
import app.miniappspring.dto.cart.ProductCartDto;
import app.miniappspring.entity.BagProduct;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-19T00:21:12+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public BagProduct toProductCart(CreateProductCartDto createProductCartDto) {
        if ( createProductCartDto == null ) {
            return null;
        }

        BagProduct bagProduct = new BagProduct();

        bagProduct.setIdProduct( createProductCartDto.getIdProduct() );
        bagProduct.setCount( createProductCartDto.getCount() );
        bagProduct.setShowInCart( createProductCartDto.isShowInCart() );

        return bagProduct;
    }

    @Override
    public ProductCartDto toCarProductDto(BagProduct cartProduct) {
        if ( cartProduct == null ) {
            return null;
        }

        ProductCartDto productCartDto = new ProductCartDto();

        productCartDto.setIdProduct( cartProduct.getIdProduct() );
        productCartDto.setCount( cartProduct.getCount() );
        productCartDto.setShowInCart( cartProduct.isShowInCart() );

        return productCartDto;
    }
}
