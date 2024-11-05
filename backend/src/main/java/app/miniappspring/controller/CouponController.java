package app.miniappspring.controller;

import app.miniappspring.dto.coupon.CouponDto;
import app.miniappspring.dto.coupon.CreateCouponDto;
import app.miniappspring.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupon")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    @GetMapping("/list")
    public List<CouponDto> getAllCouponList() {
        return couponService.getAllCouponList();
    }

    @GetMapping("/check")
    public CouponDto checkCoupon(@RequestParam String coupon){
        return couponService.checkCoupon(coupon);
    }

    @PostMapping("/add")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CouponDto> addCoupon(@RequestBody CreateCouponDto createCouponDto){
        CouponDto couponDto = couponService.addCoupon(createCouponDto);
        if(couponDto != null)
            return ResponseEntity.ok(couponDto);
        else
            return ResponseEntity.badRequest().build();
    }

}
