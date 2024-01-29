package app.miniappspring.controller.product;

import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.category.Category;
import app.miniappspring.dto.product.category.CategoryDto;
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
    public CategoryDto GetListCategory(){
      return productService.getCategories();
    }

    @PostMapping("/product/{categoryProduct}")
    public List<ProductCardDto> getProductsByCategory(@PathVariable("categoryProduct") String categoryProduct, @RequestBody Category category){
       return productService.getProductsByCategory(category);
    }
}
