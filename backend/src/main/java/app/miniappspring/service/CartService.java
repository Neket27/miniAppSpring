package app.miniappspring.service;

import app.miniappspring.dto.cart.CountProductDto;
import app.miniappspring.dto.cart.CreateProductCartDto;
import app.miniappspring.dto.cart.DtoCountProductInCart;
import app.miniappspring.dto.cart.ProductCartDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartService {

    void addProductInCart(CreateProductCartDto createProductCartDto);

    List<ProductCartDto> getListProductInCart(String accessToken);

    List<ProductCartDto> removeProductFromCart(Long idProduct, String accessToken);
    boolean increaseProductInCart(Long idProduct, String accessToken);

    @Transactional
    boolean decreaseProductInCart(Long idProduct, String accessToken);

    ProductCartDto getProductFromCart(Long idProduct, String accessToken);

    boolean sendNumberOfPiecesOfGoods(Long idProduct, int count, String accessToken);

    int sendNumberOfPiecesOfGoods(DtoCountProductInCart dtoCountProductInCart);

    int getNumberOfPiecesOfGoods(CountProductDto countProductDto);

    int getCountProductInCart(String accessToken);
}


