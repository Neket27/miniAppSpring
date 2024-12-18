package app.miniappspring.service.impl;

import app.miniappspring.dto.payData.PayDataDto;
import app.miniappspring.entity.BagProduct;
import app.miniappspring.entity.Discount;
import app.miniappspring.entity.Product;
import app.miniappspring.repository.CartRepo;
import app.miniappspring.repository.DiscountRepo;
import app.miniappspring.repository.PayRepo;
import app.miniappspring.repository.ProductRepo;
import app.miniappspring.service.PayService;
import com.google.common.util.concurrent.AtomicDouble;
import com.google.common.util.concurrent.Atomics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final PayRepo payRepo;
    private final CartRepo cartRepo;
    private final ProductRepo productRepo;
    private final DiscountRepo discountRepo;



    @Override
    @Transactional
    public PayDataDto getPayData(String city) {
        String username = (String) RequestContextHolder.currentRequestAttributes().getAttribute("username", RequestAttributes.SCOPE_REQUEST);

        List<BagProduct> bagProductList = cartRepo.getBagProductByUser_Username(username).orElse(null);


        AtomicDouble maxAmountCoupon= new AtomicDouble(0);
        PayDataDto payDataDto = bagProductList.stream()
                .map(bagProduct -> {
                    Product product = productRepo.findById(bagProduct.getIdProduct())
                            .orElseThrow(() -> new RuntimeException("Продукт не найден"));

                    Discount discount = discountRepo.findDiscountByCityAndProductList_Id(city,product.getId()).orElse(null);
                   // AtomicDouble cost = new AtomicDouble(product.getCost() * bagProduct.getCount());

                    float cost = product.getCost() * bagProduct.getCount();
                    float finalCost = cost;
//                    if(discount != null)
//                        finalCost= finalCost-discount.getAmount();

                    if(bagProduct.getCoupon()!=null)
                        maxAmountCoupon.set(Math.max(maxAmountCoupon.get(),bagProduct.getCoupon().getAmount()));
//                        finalCost= finalCost-bagProduct.getCoupon().getAmount();

                    return PayDataDto.builder()
                            .amountPay(cost)
                            .amountDeliver(0)    // Стоимость доставки можно рассчитать отдельно при необходимости
                            .amountCoupon(bagProduct.getCoupon()==null?0:bagProduct.getCoupon().getAmount())
                            .amountDiscount(discount==null?0:discount.getAmount()*bagProduct.getCount())
                            .finalAmount(cost)
                            .build();
                })
                .reduce((pay1, pay2) -> {
                            PayDataDto payData = PayDataDto.builder()
                                    .amountPay(pay1.getAmountPay() + pay2.getAmountPay())
                                    .amountDeliver(pay1.getAmountDeliver() + pay2.getAmountDeliver())
                                    .amountDiscount(pay1.getAmountDiscount() + pay2.getAmountDiscount())
                                    .amountCoupon(Math.max(pay1.getAmountCoupon(), pay2.getAmountCoupon()))
                                    .finalAmount(pay1.getFinalAmount() + pay2.getFinalAmount())
                                    .build();
                            payData.setFinalAmount(payData.getFinalAmount()-maxAmountCoupon.floatValue()-payData.getAmountDiscount());
                            return payData;
                        }
                )
                .orElseThrow(() -> new RuntimeException("Ошибка при формировании итоговой суммы"));

       // PayData payData = payRepo.findByUser_Username(username).orElseThrow(()->new RuntimeException("Данные платежа не найдены"));
    return payDataDto;
    }
}
