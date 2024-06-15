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
    private String article;
    private boolean available;
    private int stock;
    private String detail;
//    @Column(name = "image")
//    private byte[] image;
//    @Enumerated(EnumType.STRING)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_product_id")
    private CategoryProduct categoryProduct;
    private String subcategory;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Image> imageList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "characteristic_id")
    private CharacteristicProduct characteristicProduct;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "feedback_list", nullable = false)
    private List<Feedback> feedbackList;

}
