package app.miniappspring.controller;

import app.miniappspring.dto.payData.PayDataDto;
import app.miniappspring.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pay")
@RequiredArgsConstructor
public class PayController {
    private final PayService payService;

    @GetMapping("/data")
    public PayDataDto getPayDataWithDiscountsInCity(@RequestParam String city){
        return payService.getPayData(city);
    }

}
