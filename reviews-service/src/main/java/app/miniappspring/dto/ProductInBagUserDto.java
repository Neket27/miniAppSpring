package app.miniappspring.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductInBagUserDto {
    private Long id;
    private Long idProduct;
    private int count;
    private boolean showInCart;
}
