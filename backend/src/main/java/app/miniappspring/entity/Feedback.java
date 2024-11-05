package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Table(name = "feedback")
@Entity
@Getter
@Setter
public class Feedback {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nameUser;
    private String email;
    private String message;
    private float evaluation;
    private Date date;
    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "feedback_image", nullable = false)
    private List<byte[]> imageList;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
