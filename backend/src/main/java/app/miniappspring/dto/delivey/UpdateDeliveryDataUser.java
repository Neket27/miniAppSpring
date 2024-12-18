package app.miniappspring.dto.delivey;

import lombok.Data;

@Data
public class UpdateDeliveryDataUser {
    private Long id;
    private String city;
    private String address;
    private String buildingOfHouse;
    private String flat;
}
