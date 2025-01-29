package app.miniappspring.service.impl;

import app.miniappspring.dto.RequestOnGetPayDataDto;
import app.miniappspring.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    @Override
    @Transactional
    public String pay(RequestOnGetPayDataDto requestOnGetPayDataDto) {
        return "Success";
    }
}
