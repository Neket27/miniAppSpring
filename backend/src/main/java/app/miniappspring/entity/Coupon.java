package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Entity
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Integer amount;
    private Integer timeLiveInHour;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BagProduct> bagProductList;
}
