package app.miniappspring.dto.coupon;

import lombok.Data;

@Data
public class CreateCouponDto {
    private String title;
    private Integer amount;
    private Integer timeLiveInHour;
}
