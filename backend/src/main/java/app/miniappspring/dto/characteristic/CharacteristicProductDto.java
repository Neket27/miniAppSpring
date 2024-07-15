package app.miniappspring.dto.characteristic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacteristicProductDto {
    private Long id;
    private String producerCountry;
    private int sellerWarranty;
}
