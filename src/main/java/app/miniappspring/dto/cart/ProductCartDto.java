package app.miniappspring.dto.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCartDto {
    private Long idProduct;
    private String name;
    private float cost;
    private int count;
    private boolean showInCart;
}
