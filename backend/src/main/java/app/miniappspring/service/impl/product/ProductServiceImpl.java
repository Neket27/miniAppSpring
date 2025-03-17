package app.miniappspring.service.impl.product;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.arguments.UpdateProductArgument;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.dto.product.UpdateProductDto;
import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.dto.product.category.SearchProductDto;
import app.miniappspring.entity.Category;
import app.miniappspring.entity.CharacteristicProduct;
import app.miniappspring.entity.Image;
import app.miniappspring.entity.Product;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.CategoryRepo;
import app.miniappspring.repository.ProductRepo;
import app.miniappspring.service.ProductService;
import app.miniappspring.service.UserService;
import app.miniappspring.utils.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final SearchMapper searchMapper;
    private final CategoryRepo categoryRepo;
    private final ProductMapper productMapper;
    private final CharacteristicMapper characteristicMapper;
    private final ProductArgumentMapper productArgumentMapper;
    private final ImageMapper imageMapper;
    private final UserService userService;

    @Override
    @Transactional
    public ProductCardDto getProductCard(Long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new ErrorException("В базе данных нет карточки товара с id= " + id));
        return productMapper.toProductCardDto(product);
    }

    @Override
    @Transactional
    public Product findProduct(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new ErrorException("В базе данных нет карточки товара с id= " + id));
    }

    @Override
    @Transactional
//  TODO кеширует не верно  @Cacheable(value = "ProductService::getListCardProduct")
    public List<ProductCardDto> getListCardProduct() {
        List<Product> products = productRepo.findAll();
        if (products.isEmpty())
            return Collections.emptyList();
        List<ProductCardDto> list =products.stream().map(productMapper::toProductCardDto).toList();
        return list;
    }

    @Override
    @Transactional
    public List<ProductDetailDto> getListProductDetail() {
        List<Product> products = productRepo.findAll();
        List<ProductDetailDto> productDetailDtos = products.stream().map(product -> {
            List<Image> avatarList = product.getFeedbackList().stream().map(feedback -> {
                return feedback.getUser().getAvatar();
            }).toList();

            return productMapper.toProductDetailDto(product, avatarList);
        }).toList();

        return productDetailDtos;
    }

    @Override
    @Transactional
    public ProductDetailDto getProductDetailDto(Long id) {
        Product product = findProduct(id);
        List<Image> avatarList = product.getFeedbackList().stream().map(feedback -> {
            return feedback.getUser().getAvatar();
        }).toList();

        ProductDetailDto productDetailDto = productMapper.toProductDetailDto(product, avatarList);

        return productDetailDto;
    }

    @Override
    @Transactional
    public List<ProductCardDto> addProduct(CreateProductArgument createProductArgument) {
        Product product = productMapper.toProduct(createProductArgument);

        Category categoryItem = categoryRepo.findByName(createProductArgument.getCategory()).orElseThrow(() -> new RuntimeException("Категория не найдена"));
        product.setCategory(categoryItem);
        categoryItem.getProductList().add(product);

        CharacteristicProduct characteristic = productArgumentMapper.toCharacteristicProduct(createProductArgument);
        product.setCharacteristicProduct(characteristic);
        List<Image> images = imageMapper.toImageListFromCreate(createProductArgument.getCreateImageDtoList());
        product.setImageList(images);

        productRepo.save(product);
        return getListCardProduct();
    }


    @Override
    @Transactional
    public List<ProductCardDto> getProductsByCategory(CategoryDto categoryDto, int page, int pageSize) {
        List<Product> products;
        if (categoryDto.getCategoryProduct().equals("Все категории"))
            products = productRepo.findAll();
        else
            products = productRepo.findByCategory_NameContainingIgnoreCase(categoryDto.getCategoryProduct());

        return products.stream().map(product -> productMapper.toProductCardDto(product)).toList();
    }

    @Override
    @Transactional
    public List<SearchProductDto> searchProductByName(String searchText) {
        List<Product> productDtoList = productRepo.findByNameContainsIgnoreCase(searchText).orElse(null);
        if (productDtoList != null) {
            List<SearchProductDto> searchProductDtoList = productDtoList.stream().map(product -> searchMapper.toSearchProductDto(product)).toList();
            return searchProductDtoList;
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public UpdateProductDto updateProduct(UpdateProductArgument updateProductArgument) {
        Product product = productRepo.findById(updateProductArgument.getId()).orElseThrow(() -> new ErrorException("Продукт с id= " + updateProductArgument.getId() + " не найден"));

        UpdateProductDto updateProductDto = productMapper.toUpdateProductDto(updateProductArgument, product.getCharacteristicProduct());
        product = productMapper.updateProduct(product, updateProductDto);
        product.getImageList().clear();
        product.getImageList().addAll(imageMapper.toImageListFromUpdate(updateProductDto.getUpdateImageDtoList()));

        CharacteristicProduct characteristicProduct = characteristicMapper.toCharacteristicProduct(updateProductDto.getCharacteristic());
        product.getCharacteristicProduct().setProducerCountry(characteristicProduct.getProducerCountry());
        product.getCharacteristicProduct().setSellerWarranty(characteristicProduct.getSellerWarranty());

        Category categoryItem = categoryRepo.findByName(updateProductArgument.getCategory()).orElseThrow(() -> new RuntimeException("Категория не найдена"));
        product.setCategory(categoryItem);

        productRepo.save(product);
        return updateProductDto;
    }

    @Override
    @Transactional
    public void deleteProduct(long productId) {
        productRepo.deleteById(productId);
    }

    @Override
    @Transactional
    public void changeRating(float evaluation, Long idProduct, int countFeedback) {
        Product product = productRepo.findById(idProduct).orElseThrow(() -> new RuntimeException("Продукт не найтен"));
        float rating = ((product.getRating() * countFeedback) + evaluation) / (countFeedback + 1);
        product.setRating(rating);
    }

}
