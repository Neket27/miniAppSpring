package app.miniappspring.controller.search;

import app.miniappspring.dto.product.category.CategoryProductDto;
import app.miniappspring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class searchController {

    private final ProductService productService;

    @GetMapping("/search")
    public List<CategoryProductDto> searchProductByCategory(@RequestParam String category){
       return productService.searchProductByCategory(category);
    }
}
