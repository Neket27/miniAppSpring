package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PayData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float amountPay;
    private float amountDeliver;
    private float amountDiscount;

    @OneToOne
    private User user;
}
