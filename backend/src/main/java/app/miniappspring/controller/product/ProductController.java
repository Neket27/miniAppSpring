package app.miniappspring.controller.product;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.arguments.UpdateProductArgument;
import app.miniappspring.dto.image.ImageDto;
import app.miniappspring.dto.product.CreateProductDto;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.UpdateProductDto;
import app.miniappspring.entity.Image;
import app.miniappspring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor()
public class ProductController {

    private final ProductService productService;
    @GetMapping("/products")
    public List<ProductCardDto> products(){
      return   productService.getListCardProduct();
    }

    @PostMapping("/product/add")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<ProductCardDto> addProduct(@RequestBody CreateProductArgument createProductArgument){
        return productService.addProduct(createProductArgument);
    }

    @PostMapping("/product/update")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public UpdateProductDto updateProduct(@RequestBody UpdateProductArgument updateProductArgument){
        return productService.updateProduct(updateProductArgument);
    }

    @DeleteMapping("/product/delete")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public void deleteProduct(@RequestParam  Long productId){
         productService.deleteProduct(productId);
    }

    @PostMapping(path = "/product/addPhotoCardProduct", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addPhotoCardProduct(@RequestParam Long idCardPhoto, @RequestParam MultipartFile photoCardProduct) throws IOException {
      productService.addPhotoCardProduct(idCardPhoto,photoCardProduct);
    }

    @GetMapping("/product/productsInStock")
    public List<ProductCardDto> getProductsWithStock(@RequestParam String city){
      return productService.getProductsWithStock(city);
    }

    @GetMapping(
            value = "/get-image-with-media-type",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ImageDto getImage(@RequestParam Long id) {
//        InputStream in = getClass()
//                .getResourceAsStream("/image/avatar.png");
        return productService.getProductCard(id).getImageDtoList().get(0);
    }


}
