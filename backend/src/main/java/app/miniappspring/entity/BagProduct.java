package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BagProduct {
    @Id
    private Long idProduct;
    private int count;
    private boolean showInCart;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    private Coupon coupon;

    public BagProduct(Long idProduct, int count, boolean b, User user) {
        this.idProduct = idProduct;
        this.count = count;
        this.showInCart = b;
        this.user = user;
    }
}
