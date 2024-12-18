package app.miniappspring.service.impl;

import app.miniappspring.dto.coupon.CouponDto;
import app.miniappspring.dto.coupon.CreateCouponDto;
import app.miniappspring.entity.BagProduct;
import app.miniappspring.entity.Coupon;
import app.miniappspring.repository.CartRepo;
import app.miniappspring.repository.CouponRepo;
import app.miniappspring.service.CouponService;
import app.miniappspring.utils.mapper.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepo couponRepo;
    private final CouponMapper couponMapper;
    private final CartRepo cartRepo;
    
    @Override
    @Transactional
    public CouponDto checkCoupon(String coupon) {
        Coupon c =couponRepo.findByTitle(coupon).orElse(null);
        if (c != null) {
            CouponDto couponDto = couponMapper.toDto(c);
            String userName = (String) RequestContextHolder.currentRequestAttributes().getAttribute("username", RequestAttributes.SCOPE_REQUEST);
            List<BagProduct> bagProductList = cartRepo.getBagProductByUser_Username(userName).orElseThrow(() -> new RuntimeException("Продукты в карзине у полязователя с " + userName + "ненайдены"));
            List<BagProduct> bagProducts = bagProductList.stream().map(bagProduct -> {
                bagProduct.setCoupon(c);
                return bagProduct;
            }).toList();

            bagProducts.forEach(bagProduct -> cartRepo.save(bagProduct));

            return couponDto;
        }
        return null;
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
