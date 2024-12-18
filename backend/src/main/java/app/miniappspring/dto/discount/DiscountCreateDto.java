package app.miniappspring.dto.discount;

import lombok.Data;

import java.util.List;

@Data
public class DiscountCreateDto {
    private String name;
    private int amount;
    private Integer timeLiveInHour;
    private String city;
    private List<Long> productIdList;
}
