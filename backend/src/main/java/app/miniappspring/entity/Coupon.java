package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer amount;
    private Integer timeLiveInHour;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BagProduct> bagProductList;
}
