package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.dto.cart.CreateProductCartDto;
import app.miniappspring.dto.cart.ProductCartDto;
import app.miniappspring.entity.CartProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {

   CartProduct toProductCart(CreateProductCartDto createProductCartDto);

   ProductCartDto toCarProductDto(CartProduct cartProduct);

 default   List<ProductCartDto> toListProductCartDto(List<CartProduct> cartProductList){
    return cartProductList.stream().map(this::toCarProductDto).toList();
 }
}
