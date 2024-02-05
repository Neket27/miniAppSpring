package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_product_list")
@Getter
@Setter
public class CartProduct {
    @Id
    private Long idProduct;
    private int count;
    private boolean showInCart;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
