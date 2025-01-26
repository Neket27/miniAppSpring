package app.miniappspring.dto.cart;

import app.miniappspring.dto.image.ImageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCartDto {
    private Long idProduct;
    private String name;
    private float cost;
    private int count;
    private boolean showInCart;
    private List<ImageDto> imageDtoList;
}
