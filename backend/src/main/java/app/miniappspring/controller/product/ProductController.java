package app.miniappspring.controller.product;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.arguments.UpdateProductArgument;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.dto.product.UpdateProductDto;
import app.miniappspring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor()
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public List<ProductCardDto> products() {
        return productService.getListCardProduct();
    }

    @GetMapping("/detail")
    public ProductDetailDto getProductDetail(@RequestParam Long id) {
        return productService.getProductDetailDto(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE')")
    public List<ProductCardDto> addProduct(@RequestBody CreateProductArgument createProductArgument) {
        return productService.addProduct(createProductArgument);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE')")
    public UpdateProductDto updateProduct(@RequestBody UpdateProductArgument updateProductArgument) {
        return productService.updateProduct(updateProductArgument);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE')")
    public void deleteProduct(@RequestParam Long productId) {
        productService.deleteProduct(productId);
    }

//    @PostMapping(path = "/product/addPhotoCardProduct", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE')")
//    public void addPhotoCardProduct(@RequestParam Long idCardPhoto, @RequestParam MultipartFile photoCardProduct) throws IOException {
//        productService.addPhotoCardProduct(idCardPhoto, photoCardProduct);
//    }

//    @GetMapping("/product/productsInStock")
//    public List<ProductCardDto> getProductsWithStock(@RequestParam String city) {
//        return productService.getProductsWithStock(city);
//    }

}
