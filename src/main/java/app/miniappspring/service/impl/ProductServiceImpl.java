package app.miniappspring.service.impl;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.entity.CategoryProduct;
import app.miniappspring.entity.CharacteristicProduct;
import app.miniappspring.entity.Product;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.ProductRepo;
import app.miniappspring.service.ProductService;
import app.miniappspring.utils.jwtToken.mapper.ProductArgumentMapper;
import app.miniappspring.utils.jwtToken.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final ProductArgumentMapper productArgumentMapper;

    @Override
    @Transactional
    public ProductCardDto getProduct(Long id){
        Product product=productRepo.findById(id).orElseThrow(()->new ErrorException("В базе данных нет карточки товара с id= "+id));
        return productMapper.toProductCardDto(product);
    }


    @Transactional
    public Product findProduct(Long id){
        return productRepo.findById(id).orElseThrow(()->new ErrorException("В базе данных нет карточки товара с id= "+id));
    }

    @Override
    @Transactional
    public List<ProductCardDto> getListCardProduct(){
       List<Product>products=productRepo.findAll();
        List<ProductCardDto>productCardDtos=products.stream().map(product -> {
            return productMapper.toProductCardDto(product);
        }).toList();
        return productCardDtos;
    }

    @Override
    @Transactional
    public ProductDetailDto getProductDetailDto(Long id){
        Product product =findProduct(id);
       ProductDetailDto productDetailDto = productMapper.toProductDetailDto(product);

        return  productDetailDto;
    }

    @Override
    @Transactional
    public List<ProductCardDto> addProduct(CreateProductArgument createProductArgument){
        Product product=productMapper.toProduct(createProductArgument);
        CharacteristicProduct characteristic=  productArgumentMapper.toCharacteristicProduct(createProductArgument);
        product.setCharacteristicProduct(characteristic);
        productRepo.save(product);
        return getListCardProduct();
    }

    @Override
    @Transactional
    public void addPhotoCardProduct(Long idCardPhoto, MultipartFile photoCardProduct) throws IOException {
        Product product = findProduct(idCardPhoto);
        product.setImage(photoCardProduct.getBytes());
        productRepo.save(product);
    }

    @Override
    @Transactional
    public CategoryDto getListCategory(){
              List<CategoryProduct> categoryProducts = Arrays.stream(CategoryProduct.values()).map(category-> {
           Product product=productRepo.getFirstByCategoryProduct(category).orElse(null);
           if(product!=null)
                 return product.getCategoryProduct();
           return null;
       }).filter(Objects::nonNull).toList();

        return new CategoryDto(categoryProducts);
    }

}
