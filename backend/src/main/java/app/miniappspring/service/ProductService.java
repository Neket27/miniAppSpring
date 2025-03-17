package app.miniappspring.service;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.arguments.UpdateProductArgument;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.dto.product.UpdateProductDto;
import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.dto.product.category.SearchProductDto;
import app.miniappspring.entity.Product;

import java.util.List;

public interface ProductService {

    ProductCardDto getProductCard(Long id);

    Product findProduct(Long id);

    List<ProductCardDto> getListCardProduct();

    List<ProductDetailDto> getListProductDetail();

    ProductDetailDto getProductDetailDto(Long id);

    List<ProductCardDto> addProduct(CreateProductArgument createProductArgument);

    List<ProductCardDto> getProductsByCategory(CategoryDto category, int page, int pageSize);

    List<SearchProductDto> searchProductByName(String searchText);

    UpdateProductDto updateProduct(UpdateProductArgument updateProductArgument);

    void deleteProduct(long productId);

    void changeRating(float evaluation,Long productId,int countFeedBack);

}
