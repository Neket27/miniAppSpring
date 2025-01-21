package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "product_in_bag_user",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_product", "user_id"}))
@NoArgsConstructor
@Getter
@Setter
public class ProductInBagUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idProduct;
    private int count;
    private boolean showInCart;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    private Coupon coupon;

    public ProductInBagUser(Long idProduct, int count, boolean b, User user) {
        this.idProduct = idProduct;
        this.count = count;
        this.showInCart = b;
        this.user = user;
    }
}
