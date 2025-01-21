package app.miniappspring.service.impl.coupon;

import app.miniappspring.dto.coupon.CouponDto;
import app.miniappspring.dto.coupon.CreateCouponDto;
import app.miniappspring.entity.Coupon;
import app.miniappspring.entity.ProductInBagUser;
import app.miniappspring.repository.CouponRepo;
import app.miniappspring.repository.ProductsInBagUserRepo;
import app.miniappspring.service.CouponService;
import app.miniappspring.utils.mapper.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepo couponRepo;
    private final CouponMapper couponMapper;
    private final ProductsInBagUserRepo cartRepo;

    @Override
    @Transactional
    public CouponDto checkCoupon(String username, String couponName) {
        Coupon coupon = couponRepo.findByTitle(couponName).orElse(null);
        if (coupon == null)
            return null;

        CouponDto couponDto = couponMapper.toDto(coupon);
        List<ProductInBagUser> productInBagUserList = cartRepo.getBagProductByUser_Username(username).orElseThrow(() -> new RuntimeException("Продукты в карзине у полязователя с " + username + "ненайдены"));
        productInBagUserList = productInBagUserList.stream().peek(bagProduct -> bagProduct.setCoupon(coupon)).toList();

        cartRepo.saveAll(productInBagUserList);
        return couponDto;
    }

    @Override
    @Transactional
    public CouponDto addCoupon(CreateCouponDto createCouponDto) {
        Coupon coupon = couponMapper.toEntity(createCouponDto);
        Coupon couponFromBD = couponRepo.save(coupon);
        return couponMapper.toDto(couponFromBD);
        ///...
    }

    @Override
    public List<CouponDto> getAllCouponList() {
        return couponRepo.findAll().stream().map(coupon -> couponMapper.toDto(coupon)).toList();
    }

}
