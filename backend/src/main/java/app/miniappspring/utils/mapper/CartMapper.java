package app.miniappspring.utils.mapper;

import app.miniappspring.dto.cart.CreateProductCartDto;
import app.miniappspring.dto.cart.ProductCartDto;
import app.miniappspring.entity.BagProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {

   BagProduct toProductCart(CreateProductCartDto createProductCartDto);

   ProductCartDto toCarProductDto(BagProduct cartProduct);

 default   List<ProductCartDto> toListProductCartDto(List<BagProduct> cartProductList){
    return cartProductList.stream().map(this::toCarProductDto).toList();
 }
}
