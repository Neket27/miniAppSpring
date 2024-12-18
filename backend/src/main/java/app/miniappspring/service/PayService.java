package app.miniappspring.service;

import app.miniappspring.dto.payData.PayDataDto;

public interface PayService {

    PayDataDto getPayData(String city);
}
