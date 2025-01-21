package app.miniappspring.utils.mapper;

import app.miniappspring.dto.cart.CreateProductCartDto;
import app.miniappspring.dto.cart.ProductCartDto;
import app.miniappspring.entity.ProductInBagUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {

   ProductInBagUser toProductCart(CreateProductCartDto createProductCartDto);

   ProductCartDto toCarProductDto(ProductInBagUser cartProduct);

 default   List<ProductCartDto> toListProductCartDto(List<ProductInBagUser> cartProductList){
    return cartProductList.stream().map(this::toCarProductDto).toList();
 }
}
