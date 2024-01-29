package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categoryProduct")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    Category category;
    String subcategory;
    String stringValueCategory;

}
