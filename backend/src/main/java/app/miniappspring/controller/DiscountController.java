package app.miniappspring.controller;

import app.miniappspring.dto.discount.DiscountCreateDto;
import app.miniappspring.dto.discount.DiscountDto;
import app.miniappspring.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discounts")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping("")
    public List<DiscountDto> getDiscountList() {
        return discountService.getDiscountList();
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE')")
    public DiscountDto addDiscount(@RequestBody DiscountCreateDto discountCreateDto){
        return discountService.createDiscount(discountCreateDto);
    }

    @GetMapping("/check")
    public DiscountDto checkDiscount(@RequestParam Long productId){
        return discountService.checkDiscountAtProduct(productId);
    }

}
