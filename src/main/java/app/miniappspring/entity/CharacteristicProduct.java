package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "characteristic")
public class CharacteristicProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String producerCountry;
    private int sellerWarranty;
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "image", nullable = false, columnDefinition = "bytea")
    private List<byte[]> images;


}
