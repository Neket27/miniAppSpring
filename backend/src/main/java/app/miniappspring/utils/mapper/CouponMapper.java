package app.miniappspring.utils.mapper;

import app.miniappspring.dto.coupon.CouponDto;
import app.miniappspring.dto.coupon.CreateCouponDto;
import app.miniappspring.entity.Coupon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {

    Coupon toEntity(CreateCouponDto createCouponDto);
    CouponDto toDto(Coupon coupon);
}
