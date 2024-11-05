package app.miniappspring.service;

import app.miniappspring.dto.coupon.CouponDto;
import app.miniappspring.dto.coupon.CreateCouponDto;

import java.util.List;

public interface CouponService {

    CouponDto checkCoupon(String coupon);
    CouponDto addCoupon(CreateCouponDto createCouponDto);

    List<CouponDto> getAllCouponList();
}
