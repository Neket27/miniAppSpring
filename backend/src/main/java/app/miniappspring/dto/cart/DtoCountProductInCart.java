package app.miniappspring.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DtoCountProductInCart {
    private Long idProduct;
    private int count;
    private  String accessToken;
    }
