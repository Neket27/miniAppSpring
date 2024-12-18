package app.miniappspring.controller;

import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.dto.product.category.NumberOfProductsInThisCategory;
import app.miniappspring.service.CategoryService;
import app.miniappspring.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping
    public NumberOfProductsInThisCategory getMapKeyCategoryValueCountProduct() {
        return categoryService.getCategories();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE')")
    @PostMapping("/add")
    public ResponseEntity<String> createCategoryProduct(@RequestParam String categoryName, HttpServletRequest request) {
        return ResponseEntity.ok().body(categoryService.createCategoryProduct(categoryName));
    }

    @PostMapping("/products")
    public List<ProductCardDto> getProductsByCategory(@RequestBody CategoryDto category) {
        return productService.getProductsByCategory(category);
    }
}
