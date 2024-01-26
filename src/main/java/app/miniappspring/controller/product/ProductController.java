package app.miniappspring.controller.product;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.dto.product.CreateProductDto;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    public List<ProductCardDto> addProduct(@RequestBody CreateProductArgument createProductArgument){
        return productService.addProduct(createProductArgument);
    }

    @PostMapping(path = "/product/addPhotoCardProduct", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addPhotoCardProduct(@RequestParam Long idCardPhoto, @RequestParam MultipartFile photoCardProduct) throws IOException {
      productService.addPhotoCardProduct(idCardPhoto,photoCardProduct);
    }

    @GetMapping(
            value = "/get-image-with-media-type",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public  byte[] getImage(@RequestParam Long id) {
//        InputStream in = getClass()
//                .getResourceAsStream("/image/avatar.png");
        return productService.getProduct(id).getImage();
    }


}
