package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @ElementCollection(targetClass = Byte.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "id_images",referencedColumnName = "id"))
    @Column(name = "image", nullable = false)
    private List<byte[]> images;


}
