package app.miniappspring.service.impl.cart;

import app.miniappspring.dto.cart.CountProductDto;
import app.miniappspring.dto.cart.CreateProductCartDto;
import app.miniappspring.dto.cart.DtoCountProductInCart;
import app.miniappspring.dto.cart.ProductCartDto;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.entity.Product;
import app.miniappspring.entity.ProductInBagUser;
import app.miniappspring.entity.User;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.ProductsInBagUserRepo;
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
public class CartServiceImp implements CartService {

    private final ProductsInBagUserRepo bagUserRepo;
    private final CartMapper cartMapper;
    private final UserService userService;
    private final JWTService jwtService;
    private final ProductService productService;
    private final ImageMapper imageMapper;
    private final CouponService couponService;


    @Transactional
    public ProductInBagUser getProduct(Long id) {
        return bagUserRepo.findById(id).orElseThrow(() -> new ErrorException("Продукт с id= " + id + " не существует"));
    }

    @Override
    @Transactional
    public void addProductInCart(String username, CreateProductCartDto createProductCartDto) {
        ProductInBagUser createProductCart = cartMapper.toProductCart(createProductCartDto);
        bagUserRepo.save(createProductCart);
    }

    @Override
    @Transactional
    public List<ProductCartDto> getListProductInCart(String username) {
        List<ProductInBagUser> productInBagUserList = bagUserRepo.getAllByUser_UsernameOrderByIdProduct(username).orElse(Collections.emptyList());
        List<ProductCartDto> productInBagUserListDto = cartMapper.toListProductCartDto(productInBagUserList);
        return productInBagUserListDto.stream().peek(cartProduct -> {
            ProductCardDto productCardDto = productService.getProductCard(cartProduct.getIdProduct());
            cartProduct.setName(productCardDto.getName());
            cartProduct.setCost(productCardDto.getCost());
            cartProduct.setImageDtoList(productCardDto.getImageDtoList());
        }).toList();
    }

    @Override
    @Transactional
    public List<ProductCartDto> removeProductFromCart(String username, Long idProduct) {
        bagUserRepo.deleteByUser_UsernameAndIdProduct(username, idProduct);
        return getListProductInCart(username);
    }

    @Override
    @Transactional
    public boolean increaseProductInCart(String username, Long idProduct) {
        ProductInBagUser cartProduct = getProduct(idProduct);
        int countProduct = cartProduct.getCount();
        cartProduct.setCount(++countProduct);
        bagUserRepo.save(cartProduct);
        return true;
    }

    @Override
    @Transactional
    public boolean decreaseProductInCart(String username, Long idProduct) {
        ProductInBagUser cartProduct = getProduct(idProduct);
        int countProduct = cartProduct.getCount();
        cartProduct.setCount(--countProduct);
        bagUserRepo.save(cartProduct);
        return true;
    }

    @Override
    public ProductCartDto getProductFromCart(String username, Long idProduct) {
        ProductInBagUser cartProduct = bagUserRepo.getByUser_UsernameAndIdProduct(username, idProduct).orElse(null);
        if (cartProduct != null) {
            ProductCartDto productCartDto = cartMapper.toCarProductDto(cartProduct);
            Product product = productService.findProduct(idProduct);
            productCartDto.setImageDtoList(imageMapper.toImageDtoList(product.getImageList()));
            productCartDto.setName(product.getName());
            return productCartDto;
        }
        return null;

    }

    @Override
    @Transactional
    public boolean sendNumberOfPiecesOfGoods(String username, Long idProduct, int count) {
        User user = userService.getByUsername(username);
        ProductInBagUser productInBagUser = bagUserRepo.getProductInBagUserByUser_UsernameAndIdProduct(username, idProduct)
                .orElse(new ProductInBagUser(idProduct, count, true, user));
        productInBagUser.setCount(count);
        bagUserRepo.save(productInBagUser);
        return true;
    }

    @Override
    @Transactional
    public int sendNumberOfPiecesOfGoods(DtoCountProductInCart dtoCountProductInCart) {
        String username = jwtService.getUserNameFromAccessToken(dtoCountProductInCart.getAccessToken());
        User user = userService.getByUsername(username);
        if (dtoCountProductInCart.getCount() != 0) {

            ProductInBagUser productInBagUser = bagUserRepo.getProductInBagUserByUser_UsernameAndIdProduct(username, dtoCountProductInCart.getIdProduct())
                    .orElse(new ProductInBagUser(dtoCountProductInCart.getIdProduct(), dtoCountProductInCart.getCount(), true, user));

            productInBagUser.setCount(dtoCountProductInCart.getCount());

            bagUserRepo.save(productInBagUser);
        } else {
            bagUserRepo.removeByUser_UsernameAndIdProduct(username, dtoCountProductInCart.getIdProduct());
        }

        return dtoCountProductInCart.getCount();
    }

    @Override
    @Transactional
    public int getCountProductInCart(String username) {
        return bagUserRepo.countCartProductByUser_Username(username).orElse(0);
    }

    @Override
    @Transactional
    public int getNumberOfPiecesOfGoods(CountProductDto countProductDto) {
        String username = jwtService.getUserNameFromAccessToken(countProductDto.getAccessToken());
        ProductInBagUser productInBagUser = bagUserRepo.getProductInBagUserByUser_UsernameAndIdProduct(username, countProductDto.getIdProduct())
//                .orElseThrow(() -> new ErrorException("Количество штук выбранного не получено. Нет такого продукта у пользователя"));
                .orElse(null);
        if(productInBagUser == null)
            return 0;
        return productInBagUser.getCount();
    }

}
