package app.miniappspring.controller.bag;

import app.miniappspring.dto.cart.CountProductDto;
import app.miniappspring.dto.cart.CreateProductCartDto;
import app.miniappspring.dto.cart.DtoCountProductInCart;
import app.miniappspring.dto.cart.ProductCartDto;
import app.miniappspring.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/products")
    public List<ProductCartDto> getListProductInCart() {
        return cartService.getListProductInCart(getUsernameFromRequestAttributes());
    }

    @GetMapping("/product")
    public ProductCartDto getProductFromCart(@RequestParam Long idProduct) {
        return cartService.getProductFromCart(getUsernameFromRequestAttributes(), idProduct);
    }

    @PostMapping("/add")
    public void addProductInCart(@RequestBody CreateProductCartDto createProductCartDto) {
        cartService.addProductInCart(getUsernameFromRequestAttributes(),createProductCartDto);
    }

    @GetMapping("/remove")
    public List<ProductCartDto> removeProductFromCart(@RequestParam Long idProduct) {
        return cartService.removeProductFromCart(getUsernameFromRequestAttributes(), idProduct);
    }


    //TODO не используется
    @GetMapping("/increase")
    public boolean increaseProductInCart(@RequestParam Long idProduct) {
        return cartService.increaseProductInCart(getUsernameFromRequestAttributes(), idProduct);
    }

    //TODO не используется
    @GetMapping("/decrease")
    public boolean decreaseProductInCartProductInCart(@RequestParam Long idProduct) {
        return cartService.decreaseProductInCart(getUsernameFromRequestAttributes(), idProduct);
    }

    @GetMapping("/sendCountProductInCart")
    public boolean sendCountProductInCart(@RequestParam Long idProduct, @RequestParam int count) {
        return cartService.sendNumberOfPiecesOfGoods(getUsernameFromRequestAttributes(), idProduct, count);
    }

    @GetMapping("/products/count")
    public int getCountProductInCart() {
        return cartService.getCountProductInCart(getUsernameFromRequestAttributes());
    }

    @MessageMapping("/sendNumberOfPiecesOfGoods")
    @SendTo("/shoppingCart/public")
    public int sendCountProductInCart(@Payload DtoCountProductInCart dtoCountProductInCart) {
        return cartService.sendNumberOfPiecesOfGoods(dtoCountProductInCart);
    }

    @MessageMapping("/getNumberOfPiecesOfGoods")
    @SendTo("/shoppingCart/public")
    public int getNumberOfPiecesOfGoods(@Payload CountProductDto countProductDto) {
        return cartService.getNumberOfPiecesOfGoods(countProductDto);
    }

    @MessageMapping("/getCountProductInCart")
    @SendTo("/shoppingCartCountProduct/public")
    public int getCountProductInCart2(@Payload String accessToken) {
        return cartService.getCountProductInCart(accessToken);
    }

    private String getUsernameFromRequestAttributes() {
        return Optional.ofNullable(RequestContextHolder.currentRequestAttributes().getAttribute("username", RequestAttributes.SCOPE_REQUEST))
                .map(Object::toString)
                .orElse(null);
    }

}
