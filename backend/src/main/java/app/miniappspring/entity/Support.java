package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
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
