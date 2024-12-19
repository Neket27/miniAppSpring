package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class DeliveryDataUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String address;
    private String buildingOfHouse;
    private String flat;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
}
