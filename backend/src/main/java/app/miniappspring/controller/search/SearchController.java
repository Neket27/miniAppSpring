package app.miniappspring.controller.search;

import app.miniappspring.dto.product.category.SearchProductDto;
import app.miniappspring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/search")
@RequiredArgsConstructor
public class SearchController {
    private final ProductService productService;

    @GetMapping("")
    public List<SearchProductDto> searchProductByCategory(@RequestParam String searchText) {
        return productService.searchProductByName(searchText);
    }
}
