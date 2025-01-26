package app.miniappspring.utils.mapper;

import app.miniappspring.dto.coupon.CouponDto;
import app.miniappspring.dto.coupon.CreateCouponDto;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T16:17:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class CouponMapperImpl implements CouponMapper {

    @Override
    public Coupon toEntity(CreateCouponDto createCouponDto) {
        if ( createCouponDto == null ) {
            return null;
        }

        Coupon coupon = new Coupon();

        coupon.setTitle( createCouponDto.getTitle() );
        coupon.setAmount( createCouponDto.getAmount() );
        coupon.setTimeLiveInHour( createCouponDto.getTimeLiveInHour() );

        return coupon;
    }

    @Override
    public CouponDto toDto(Coupon coupon) {
        if ( coupon == null ) {
            return null;
        }

        CouponDto couponDto = new CouponDto();

        couponDto.setId( coupon.getId() );
        couponDto.setTitle( coupon.getTitle() );
        couponDto.setAmount( coupon.getAmount() );
        couponDto.setTimeLiveInHour( coupon.getTimeLiveInHour() );

        return couponDto;
    }
}
