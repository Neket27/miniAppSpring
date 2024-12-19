package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "product")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float cost;
    private float rating;
    private String description;
    private String brand;
    private String note;
    private boolean available;
    private int stock;
    private String detail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private CategoryItem categoryItem;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Image> imageList;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "characteristic_id")
    private CharacteristicProduct characteristicProduct;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, orphanRemoval = true)
    @Column(name = "feedback_list", nullable = true)
    private List<Feedback> feedbackList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_discount", // Имя промежуточной таблицы
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "discount_id")
    )
    private List<Discount> discountList;

}
