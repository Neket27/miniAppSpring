package app.miniappspring.service.impl.cart;

import app.miniappspring.dto.cart.CreateProductCartDto;
import app.miniappspring.dto.cart.ProductCartDto;
import app.miniappspring.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AICartService {

    private final CartService cartService;

    @Bean
    @Description("Adding an item to the shopping cart")
    public Consumer<RequestAddProductInCart> addProductInCart () {
        return requestAddProductInCart -> cartService.addProductInCart(requestAddProductInCart.username(),requestAddProductInCart.createProductCartDto());
    }

    @Bean
    @Description("Get list item in a shopping cart user")
    public Function<RequestGetListProductInCart, List<ProductCartDto>> getListProductInCart(){
        return requestGetListProductInCart -> cartService.getListProductInCart(requestGetListProductInCart.username());
    }

    @Bean
    @Description("The function of removing a product from the user's shopping cart. The function's input is username and product Id.")
    public Function<RequestDeleteProductInCart,List<ProductCartDto>> removeProductInCart () {
        return requestDeleteProductInCart -> cartService.removeProductFromCart(requestDeleteProductInCart.username(),requestDeleteProductInCart.productId());
    }

    @Bean
    @Description("Increasing the number of products in a shopping cart")
    public Function<RequestIncreaseProductInCart,Boolean> increaseProductInCart(){
        return requestIncreaseProductInCart -> cartService.increaseProductInCart(requestIncreaseProductInCart.username(), requestIncreaseProductInCart.idProduct());
    }

    @Bean
    @Description("Reducing the product in a shopping cart")
    public Function<RequestDecreaseProductInCart,Boolean>  decreaseProductInCart () {
        return requestDecreaseProductInCart -> cartService.decreaseProductInCart(requestDecreaseProductInCart.username(), requestDecreaseProductInCart.idProduct());
    }

    @Bean
    @Description("Get count a product in a shopping cart user")
    public Function<RequestGetCountProductInCart,Integer> getCountProductInCart(){
        return requestGetCountProductInCart -> cartService.getCountProductInCart(requestGetCountProductInCart.username());
    }
}

record RequestAddProductInCart(String username,CreateProductCartDto createProductCartDto){}
record RequestGetListProductInCart(String username){}
record RequestDeleteProductInCart(String username, Long productId){}
record RequestIncreaseProductInCart(String username, Long idProduct){}
record RequestDecreaseProductInCart(String username, Long idProduct){}
record RequestGetCountProductInCart(String username){}