package app.miniappspring.service.impl;

import app.miniappspring.dto.cart.CountProductDto;
import app.miniappspring.dto.cart.CreateProductCartDto;
import app.miniappspring.dto.cart.DtoCountProductInCart;
import app.miniappspring.dto.cart.ProductCartDto;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.entity.BagProduct;
import app.miniappspring.entity.Product;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.CartRepo;
import app.miniappspring.service.*;
import app.miniappspring.utils.mapper.CartMapper;
import app.miniappspring.utils.mapper.ImageMapper;
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
    private final JWTService jwtService;
    private final ProductService productService;
    private final ImageMapper imageMapper;
    private final CouponService couponService;


    @Transactional
    public BagProduct getProduct(Long id) {
       return cartRepo.findById(id).orElseThrow(()->new ErrorException("Продукт с id= "+id+" не существует"));
    }

    @Override
    @Transactional
    public void addProductInCart(CreateProductCartDto createProductCartDto){
        BagProduct createProductCart = cartMapper.toProductCart(createProductCartDto);
        String username = jwtService.getUserNameFromAccessToken(createProductCartDto.getAccessToken());
        createProductCart.setUser(userService.getByUsername(username));
        cartRepo.save(createProductCart);
    }

    @Override
    @Transactional
    public List<ProductCartDto> getListProductInCart(String accessToken) {
        String username = jwtService.getUserNameFromAccessToken(accessToken);
        List<BagProduct> cartProductList = cartRepo.getAllByUser_UsernameOrderByIdProduct(username).orElse(Collections.emptyList());
        List<ProductCartDto> productCartListDto = cartMapper.toListProductCartDto(cartProductList);
        return productCartListDto.stream().peek(cartProduct -> {
            ProductCardDto productCardDto = productService.getProductCard(cartProduct.getIdProduct());
            cartProduct.setName(productCardDto.getName());
            cartProduct.setCost(productCardDto.getCost());
            cartProduct.setImageDtoList(productCardDto.getImageDtoList());
        }).toList();
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
        BagProduct cartProduct = getProduct(idProduct);
            int countProduct =cartProduct.getCount();
            cartProduct.setCount(++countProduct);
            cartRepo.save(cartProduct);
            return true;
    }

    @Override
    @Transactional
    public boolean decreaseProductInCart(Long idProduct, String accessToken) {
        BagProduct cartProduct = getProduct(idProduct);
        int countProduct =cartProduct.getCount();
        cartProduct.setCount(--countProduct);
        cartRepo.save(cartProduct);
        return true;
    }

    @Override
    public ProductCartDto getProductFromCart(Long idProduct, String accessToken) {
        String username = jwtService.getUserNameFromAccessToken(accessToken);
        BagProduct cartProduct = cartRepo.getByUser_UsernameAndIdProduct(username, idProduct).orElse(null);
        if (cartProduct != null) {
            ProductCartDto productCartDto =cartMapper.toCarProductDto(cartProduct);
            Product product = productService.findProduct(idProduct);
            productCartDto.setImageDtoList(imageMapper.toImageDtoList(product.getImageList()));
            productCartDto.setName(product.getName());
            return productCartDto;
        }
        return null;

    }

    @Override
    @Transactional
    public boolean sendNumberOfPiecesOfGoods(Long idProduct, int count, String accessToken) {
        BagProduct cartProduct = getProduct(idProduct);
        cartProduct.setCount(count);
        cartRepo.save(cartProduct);
        return true;
    }

    @Override
    @Transactional
    public int sendNumberOfPiecesOfGoods(DtoCountProductInCart dtoCountProductInCart) {
        String username= jwtService.getUserNameFromAccessToken(dtoCountProductInCart.getAccessToken());
        if(dtoCountProductInCart.getCount()!=0) {
            BagProduct cartProduct = cartRepo.getCartProductByUser_UsernameAndAndIdProduct(username, (dtoCountProductInCart.getIdProduct())).orElse(new BagProduct(dtoCountProductInCart.getIdProduct(), dtoCountProductInCart.getCount(), true, userService.getByUsername(username)));
            cartProduct.setCount(dtoCountProductInCart.getCount());
            cartRepo.save(cartProduct);
        }else {
            cartRepo.removeByUser_UsernameAndIdProduct(username, dtoCountProductInCart.getIdProduct());
        }

        return dtoCountProductInCart.getCount();
    }

    @Override
    @Transactional
    public int getCountProductInCart(String accessToken) {
      String username= jwtService.getUserNameFromAccessToken(accessToken);
        return cartRepo.countCartProductByUser_Username(username).orElse(0);
    }

    @Override
    @Transactional
    public int getNumberOfPiecesOfGoods(CountProductDto countProductDto) {
        String username= jwtService.getUserNameFromAccessToken(countProductDto.getAccessToken());
        BagProduct cartProduct = cartRepo.getCartProductByUser_UsernameAndAndIdProduct(username, countProductDto.getIdProduct()).orElseThrow(() -> new ErrorException("Количество штук выбранного не получено. Нет такого продукта у пользователя"));
        return cartProduct.getCount();
    }

}
