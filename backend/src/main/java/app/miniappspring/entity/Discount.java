package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int amount;
    private String city;

    @ManyToMany(mappedBy = "discountList")
    private List<Product> productList;
}
