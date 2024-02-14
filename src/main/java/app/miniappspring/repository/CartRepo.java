package app.miniappspring.repository;

import app.miniappspring.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<CartProduct,Long> {

    Optional<List<CartProduct>> getAllByUser_UsernameOrderByIdProduct(String username);
    Optional<CartProduct> getByUser_UsernameAndIdProduct(String username, Long idProduct);
    int countCartProductByUser_Username(String username);
}
