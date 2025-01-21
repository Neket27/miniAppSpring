package app.miniappspring.controller;

import app.miniappspring.dto.coupon.CouponDto;
import app.miniappspring.dto.coupon.CreateCouponDto;
import app.miniappspring.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    @GetMapping("")
    public List<CouponDto> getAllCouponList() {
        return couponService.getAllCouponList();
    }

    @GetMapping("/check")
    public CouponDto checkCoupon(@RequestParam String coupon) {
        String username = (String) RequestContextHolder.currentRequestAttributes().getAttribute("username", RequestAttributes.SCOPE_REQUEST);
        return couponService.checkCoupon(username, coupon);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<CouponDto> addCoupon(@RequestBody CreateCouponDto createCouponDto) {
        CouponDto couponDto = couponService.addCoupon(createCouponDto);
        if (couponDto == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(couponDto);
    }

}
