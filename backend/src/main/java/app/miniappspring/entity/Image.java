package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Image {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private String contentType;
    @Lob
    @Column(nullable = false)
    private byte[] bytes;

    @OneToOne
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;

}
