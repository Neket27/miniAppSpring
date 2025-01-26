package app.miniappspring.dto.payData;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayDataDto {
    private float amountPay;
    private float amountDeliver;
    private float amountDiscount;
    private float amountCoupon;
    private float finalAmount;
}
