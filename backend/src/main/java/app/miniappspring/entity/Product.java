package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.List;

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
    private String note;
    private boolean available;
    private int stock;
    private String detail;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "category_product_id")
//    private CategoryProduct categoryProduct;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CategoryItem categoryItem;
//    private String subcategory;

//    @OneToMany(fetch = FetchType.LAZY,orphanRemoval = true)
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Image> imageList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "characteristic_id")
    private CharacteristicProduct characteristicProduct;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
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
