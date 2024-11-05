package app.miniappspring.service;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.arguments.UpdateProductArgument;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.dto.product.UpdateProductDto;
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

    ProductCardDto getProductCard(Long id);

    Product findProduct(Long id);

    List<ProductCardDto> getListCardProduct();

    ProductDetailDto getProductDetailDto(Long id);

    List<ProductCardDto> addProduct(CreateProductArgument createProductArgument);

    void addPhotoCardProduct(Long idCardPhoto, MultipartFile photoCardProduct) throws IOException;

    NumberOfProductsInThisCategory getCategories();

    List<ProductCardDto> getProductsByCategory(CategoryDto category);

    List<CategoryProductDto>searchProductByCategory(String category);

    UpdateProductDto updateProduct(UpdateProductArgument updateProductArgument);

    void deleteProduct(long productId);
    Product saveProduct(Product product);

    void changeRating(float evaluation,Long productId,int countFeedBack);

    List<ProductCardDto> getProductsWithStock(String city);
}
