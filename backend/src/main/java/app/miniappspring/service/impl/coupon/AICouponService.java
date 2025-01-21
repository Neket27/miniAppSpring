package app.miniappspring.service.impl.coupon;

import app.miniappspring.dto.coupon.CouponDto;
import app.miniappspring.dto.coupon.CreateCouponDto;
import app.miniappspring.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class AICouponService {

    private final CouponService couponService;

    @Bean
    @Description("Check exist a coupon")
    public Function<RequestCheckCoupon,CouponDto> checkCoupon(){
        return requestCheckCoupon -> couponService.checkCoupon(requestCheckCoupon.username(),requestCheckCoupon.nameCoupon());
    }

    @Bean
    @Description("Call this function when the user wants to create a coupon. The coupon creation function accepts the Create Coupon object as input, which consists of the coupon name, amount, " +
            "and expiration date in hours.")
    public Function<RequestOnCreateCoupon,CouponDto> createCoupon(){
        return requestOnCreateCoupon -> couponService.addCoupon(requestOnCreateCoupon.createCouponDto());
    }

    @Bean
    @Description("A function that returns all active coupons.")
    public Supplier<List<CouponDto>> getListCoupon(){
        return ()-> couponService.getAllCouponList();
    }
}

record RequestCheckCoupon(String username, String nameCoupon){}
record RequestOnCreateCoupon(CreateCouponDto createCouponDto){}

