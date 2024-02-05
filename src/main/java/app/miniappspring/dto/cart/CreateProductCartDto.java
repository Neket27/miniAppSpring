package app.miniappspring.dto.cart;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateProductCartDto {
    private Long idProduct;
    private String accessToken;
    private int count;

}
