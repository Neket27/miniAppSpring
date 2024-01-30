package app.miniappspring.service;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.dto.product.category.NumberOfProductsInThisCategory;
import app.miniappspring.dto.product.category.CategoryProductDto;
import app.miniappspring.entity.Feedback;
import app.miniappspring.entity.Product;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {


    @Transactional
    ProductCardDto getProduct(Long id);

    @Transactional
    Product findProduct(Long id);

    @Transactional
    List<ProductCardDto> getListCardProduct();

    @Transactional
    ProductDetailDto getProductDetailDto(Long id);


    @Transactional
    List<ProductCardDto> addProduct(CreateProductArgument createProductArgument);

    @Transactional
    void addPhotoCardProduct(Long idCardPhoto, MultipartFile photoCardProduct) throws IOException;

    @Transactional
    NumberOfProductsInThisCategory getCategories();

    @Transactional
    List<ProductCardDto> getProductsByCategory(CategoryDto category);

    @Transactional
    List<CategoryProductDto>searchProductByCategory(String category);

}
