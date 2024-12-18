package app.miniappspring.dto.coupon;

import lombok.Data;

@Data
public class CouponDto {
    private Long id;
    private String title;
    private Integer amount;
    private Integer timeLiveInHour;
}
