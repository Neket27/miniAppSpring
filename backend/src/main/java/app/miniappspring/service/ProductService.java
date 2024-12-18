package app.miniappspring.service;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.arguments.UpdateProductArgument;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.dto.product.UpdateProductDto;
import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.dto.product.category.NumberOfProductsInThisCategory;
import app.miniappspring.dto.product.category.SearchProductDto;
import app.miniappspring.entity.Product;
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



    List<ProductCardDto> getProductsByCategory(CategoryDto category);

    List<SearchProductDto> searchProductByName(String searchText);

    UpdateProductDto updateProduct(UpdateProductArgument updateProductArgument);

    void deleteProduct(long productId);
    Product saveProduct(Product product);

    void changeRating(float evaluation,Long productId,int countFeedBack);

    List<ProductCardDto> getProductsWithStock(String city);
}
