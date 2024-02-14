package app.miniappspring.service;

import app.miniappspring.dto.cart.CreateProductCartDto;
import app.miniappspring.dto.cart.ProductCartDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface CartService {

    void addProductInCart(CreateProductCartDto createProductCartDto);

    List<ProductCartDto> getListProductInCart(String accessToken);

    List<ProductCartDto> removeProductFromCart(Long idProduct, String accessToken);
    boolean increaseProductInCart(Long idProduct, String accessToken);

    @Transactional
    boolean decreaseProductInCart(Long idProduct, String accessToken);

    ProductCartDto getProductFromCart(Long idProduct, String accessToken);

    boolean sendCountProductInCart(Long idProduct, int count, String accessToken);

    int getCountProductInCart(String accessToken);
}


