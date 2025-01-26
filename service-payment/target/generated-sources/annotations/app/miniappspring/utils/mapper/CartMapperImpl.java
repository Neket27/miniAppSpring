package app.miniappspring.utils.mapper;

import app.miniappspring.dto.cart.CreateProductCartDto;
import app.miniappspring.dto.cart.ProductCartDto;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T16:17:56+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public ProductInBagUser toProductCart(CreateProductCartDto createProductCartDto) {
        if ( createProductCartDto == null ) {
            return null;
        }

        ProductInBagUser productInBagUser = new ProductInBagUser();

        productInBagUser.setIdProduct( createProductCartDto.getIdProduct() );
        productInBagUser.setCount( createProductCartDto.getCount() );
        productInBagUser.setShowInCart( createProductCartDto.isShowInCart() );

        return productInBagUser;
    }

    @Override
    public ProductCartDto toCarProductDto(ProductInBagUser cartProduct) {
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
