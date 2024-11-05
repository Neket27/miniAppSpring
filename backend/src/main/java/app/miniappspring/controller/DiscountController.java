package app.miniappspring.controller;

import app.miniappspring.dto.discount.DiscountCreateDto;
import app.miniappspring.dto.discount.DiscountDto;
import app.miniappspring.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discount")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @GetMapping("/list")
    public List<DiscountDto> getDiscountList() {
        return discountService.getDiscountList();
    }

    @PostMapping("/create")
    public DiscountDto createDiscount(@RequestBody DiscountCreateDto discountCreateDto){
        return discountService.createDiscount(discountCreateDto);
    }

    @GetMapping("/check")
    public DiscountDto checkDiscount(@RequestParam Long productId){
        return discountService.checkDiscountAtProduct(productId);
    }

}
