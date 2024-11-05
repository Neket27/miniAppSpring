package app.miniappspring.controller.product;

import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.dto.product.category.NumberOfProductsInThisCategory;
import app.miniappspring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class ProductCategoryController {
private final ProductService productService;
    @GetMapping
    public NumberOfProductsInThisCategory GetListCategory(){
      return productService.getCategories();
    }

    @PostMapping("/product")
    public List<ProductCardDto> getProductsByCategory(@RequestBody CategoryDto category){
       return productService.getProductsByCategory(category);
    }
}
