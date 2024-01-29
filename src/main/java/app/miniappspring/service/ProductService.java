package app.miniappspring.service;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.dto.product.CreateProductDto;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.dto.product.category.Category;
import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.entity.CategoryProduct;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {


    @Transactional
    ProductCardDto getProduct(Long id);

    @Transactional
    List<ProductCardDto> getListCardProduct();

    @Transactional
    ProductDetailDto getProductDetailDto(Long id);


    @Transactional
    List<ProductCardDto> addProduct(CreateProductArgument createProductArgument);

    @Transactional
    void addPhotoCardProduct(Long idCardPhoto, MultipartFile photoCardProduct) throws IOException;

    @Transactional
    CategoryDto getCategories();

    @Transactional
    List<ProductCardDto> getProductsByCategory(Category category);

    @Transactional
    List<CategoryProduct>searchProductByCategory(String category);
}
