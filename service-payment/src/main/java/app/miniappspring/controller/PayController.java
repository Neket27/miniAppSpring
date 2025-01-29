package app.miniappspring.controller;

import app.miniappspring.dto.RequestOnGetPayDataDto;
import app.miniappspring.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class PayController {
    private final PayService payService;

    @GetMapping("/pay")
    public String pay(@RequestParam RequestOnGetPayDataDto requestOnGetPayDataDto) {
        return payService.pay(requestOnGetPayDataDto);
    }
}
