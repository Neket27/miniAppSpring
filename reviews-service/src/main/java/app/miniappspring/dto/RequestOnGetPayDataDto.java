package app.miniappspring.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class RequestOnGetPayDataDto {
    private Long userId;
    private BigDecimal amount;
}
