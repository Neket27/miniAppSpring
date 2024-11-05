package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DeliveryDataUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String city;
    private String address;
    private String buildingOfHouse;
    private String flat;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
}
