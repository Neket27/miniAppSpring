package app.miniappspring.controller.product;

import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.service.ProductService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductDetailController {

    private final ProductService productService;

    @GetMapping("/productDetail")
    public ProductDetailDto getProductDetail(@RequestParam Long id) {
        return productService.getProductDetailDto(id);

    }
}

//    @PostMapping("/bytes")
//    public void testBytes(@RequestBody TestBytesDto testBytesDto){
//        System.out.println(Arrays.toString(testBytesDto.getBytes()));
//    }
//}
//
//@Setter
//@Getter
//
//class TestBytesDto{
//    public String name;
//    @Schema(type = "string", format = "binary", description = "Binary data")
//    public byte[]bytes;
//}
