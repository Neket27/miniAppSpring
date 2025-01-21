package app.miniappspring.service;

import app.miniappspring.dto.cart.CountProductDto;
import app.miniappspring.dto.cart.CreateProductCartDto;
import app.miniappspring.dto.cart.DtoCountProductInCart;
import app.miniappspring.dto.cart.ProductCartDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartService {

    void addProductInCart(String username, CreateProductCartDto createProductCartDto);

    List<ProductCartDto> getListProductInCart(String username);

    List<ProductCartDto> removeProductFromCart(String username, Long idProduct);
    boolean increaseProductInCart(String username, Long idProduct);

    @Transactional
    boolean decreaseProductInCart(String username, Long idProduct);

    ProductCartDto getProductFromCart(String username, Long idProduct);

    boolean sendNumberOfPiecesOfGoods(String username, Long idProduct, int count);

    int sendNumberOfPiecesOfGoods(DtoCountProductInCart dtoCountProductInCart);

    int getNumberOfPiecesOfGoods(CountProductDto countProductDto);

    int getCountProductInCart(String username);

}


