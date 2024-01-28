package app.miniappspring.controller.product;

import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class ProductCategoryController {
private final ProductService productService;
    @GetMapping
    public CategoryDto GetListCategory(){
      return   productService.getListCategory();
    }
}
