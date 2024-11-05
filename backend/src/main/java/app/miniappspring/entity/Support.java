package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Support {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String nameUser;
    private String email;
    private String message;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> imageList;
}
