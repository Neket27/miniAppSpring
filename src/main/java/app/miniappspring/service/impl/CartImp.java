package app.miniappspring.service.impl;

import app.miniappspring.dto.cart.CreateProductCartDto;
import app.miniappspring.dto.cart.ProductCartDto;
import app.miniappspring.entity.CartProduct;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.CartRepo;
import app.miniappspring.service.CartService;
import app.miniappspring.service.JWTService;
import app.miniappspring.service.ProductService;
import app.miniappspring.service.UserService;
import app.miniappspring.utils.jwtToken.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartImp implements CartService {

    private final CartRepo cartRepo;
    private final CartMapper cartMapper;
    private final UserService userService;
    private  final JWTService jwtService;
    private final ProductService productService;


    @Transactional
    public CartProduct getProduct(Long id){
       return cartRepo.findById(id).orElseThrow(()->new ErrorException("Продукт с id= "+id+" не существует"));
    }
    @Override
    @Transactional
    public void addProductInCart(CreateProductCartDto createProductCartDto){
        CartProduct createProductCart = cartMapper.toProductCart(createProductCartDto);
        String username = jwtService.getUserNameFromAccessToken(createProductCartDto.getAccessToken());
        createProductCart.setUser(userService.getByUsername(username));
        cartRepo.save(createProductCart);
    }

    @Override
    @Transactional
    public List<ProductCartDto> getListProductInCart(String accessToken) {
        String username = jwtService.getUserNameFromAccessToken(accessToken);
        List<CartProduct> cartProductList = cartRepo.getAllByUser_UsernameOrderByIdProduct(username).orElse(Collections.emptyList());
        List<ProductCartDto> productCartListDto = cartMapper.toListProductCartDto(cartProductList);
        return productCartListDto.stream().peek(cartProduct -> cartProduct.setName(productService.getProduct(cartProduct.getIdProduct()).getName())).toList();
    }

    @Override
    @Transactional
    public List<ProductCartDto> removeProductFromCart(Long idProduct, String accessToken) {
            cartRepo.deleteById(idProduct);
            return getListProductInCart(accessToken);
    }

    @Override
    @Transactional
    public boolean increaseProductInCart(Long idProduct, String accessToken) {
            CartProduct cartProduct= getProduct(idProduct);
            int countProduct =cartProduct.getCount();
            cartProduct.setCount(++countProduct);
            cartRepo.save(cartProduct);
            return true;
    }

    @Override
    @Transactional
    public boolean decreaseProductInCart(Long idProduct, String accessToken) {
        CartProduct cartProduct= getProduct(idProduct);
        int countProduct =cartProduct.getCount();
        cartProduct.setCount(--countProduct);
        cartRepo.save(cartProduct);
        return true;
    }

    @Override
    public ProductCartDto getProductFromCart(Long idProduct, String accessToken) {
        String username = jwtService.getUserNameFromAccessToken(accessToken);
        CartProduct cartProduct = cartRepo.getByUser_UsernameAndIdProduct(username,idProduct).orElse(null);
        if (cartProduct != null) {
            ProductCartDto productCartDto =cartMapper.toCarProductDto(cartProduct);
            productCartDto.setName(productService.findProduct(idProduct).getName());
            return productCartDto;
        }
        return null;

    }

    @Override
    @Transactional
    public boolean sendCountProductInCart(Long idProduct, int count, String accessToken) {
        CartProduct cartProduct= getProduct(idProduct);
        cartProduct.setCount(count);
        cartRepo.save(cartProduct);
        return true;
    }

}
