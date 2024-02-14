package app.miniappspring.controller.cart;

import app.miniappspring.dto.cart.CreateProductCartDto;
import app.miniappspring.dto.cart.ProductCartDto;
import app.miniappspring.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/getProduct")
    public ProductCartDto getProductFromCart(@RequestParam Long idProduct, @RequestParam String accessToken){
        return cartService.getProductFromCart(idProduct,accessToken);
    }

    @PostMapping("/add")
    public void addProductInCart(@RequestBody CreateProductCartDto createProductCartDto){
        cartService.addProductInCart(createProductCartDto);
    }

    @GetMapping
    public List<ProductCartDto> getListProductInCart(@RequestParam String accessToken){
        return cartService.getListProductInCart(accessToken);
    }

    @GetMapping("/remove")
    public List<ProductCartDto> removeProductFromCart(@RequestParam Long idProduct, @RequestParam String accessToken){
        return cartService.removeProductFromCart(idProduct, accessToken);
    }

    @GetMapping("/increase")
    public boolean increaseProductInCart(@RequestParam Long idProduct, @RequestParam String accessToken){
        return cartService.increaseProductInCart(idProduct, accessToken);
    }

    @GetMapping("/decrease")
    public boolean  decreaseProductInCartProductInCart(@RequestParam Long idProduct, @RequestParam String accessToken){
        return cartService.decreaseProductInCart(idProduct, accessToken);
    }

    @GetMapping("/sendCountProductInCart")
    public boolean  sendCountProductInCart(@RequestParam Long idProduct,@RequestParam int count ,@RequestParam String accessToken){
        return cartService.sendCountProductInCart(idProduct,count, accessToken);
    }

    @GetMapping("/count")
    public int getCountProductInCart(@RequestParam String accessToken){
        return cartService.getCountProductInCart(accessToken);
    }
}
