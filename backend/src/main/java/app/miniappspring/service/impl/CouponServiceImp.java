package app.miniappspring.service.impl;

import app.miniappspring.dto.coupon.CouponDto;
import app.miniappspring.dto.coupon.CreateCouponDto;
import app.miniappspring.entity.Coupon;
import app.miniappspring.repository.CouponRepo;
import app.miniappspring.service.CouponService;
import app.miniappspring.utils.jwtToken.mapper.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImp implements CouponService {
    private final CouponRepo couponRepo;
    private final CouponMapper couponMapper;
    
    @Override
    @Transactional
    public CouponDto checkCoupon(String coupon) {
        return couponMapper.toDto(couponRepo.findByTitle(coupon).orElse(null));
    }

    @Override
    @Transactional
    public CouponDto addCoupon(CreateCouponDto createCouponDto) {
        Coupon coupon = couponMapper.toEntity(createCouponDto);
//        try {
            Coupon couponFromBD = couponRepo.save(coupon);
//        }catch (Exception e){
//            return null;
//        }
        return couponMapper.toDto(couponFromBD);

    }

    @Override
    public List<CouponDto> getAllCouponList() {
        return couponRepo.findAll().stream().map(coupon -> couponMapper.toDto(coupon)).toList();
    }

}
