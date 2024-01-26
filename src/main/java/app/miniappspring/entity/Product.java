package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private float cost;
    private float rating;
    private String description;
    private String brand;
    private String article;
    private boolean available;
    private int stock;
    private String detail;

    @Column(name = "image")
    private byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "characteristic_id", referencedColumnName = "id")
    private CharacteristicProduct characteristicProduct;

}
