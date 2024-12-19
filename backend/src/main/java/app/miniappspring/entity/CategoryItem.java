package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CategoryItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "categoryItem", fetch = FetchType.LAZY)
    private List<Product> productList;

    public CategoryItem(String name) {
        this.name = name;
    }
}
