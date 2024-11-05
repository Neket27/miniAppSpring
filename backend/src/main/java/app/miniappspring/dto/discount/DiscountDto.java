package app.miniappspring.dto.discount;

import lombok.Data;

@Data
public class DiscountDto {
    private Long id;
    private String name;
    private int amount;
    private String city;
}
