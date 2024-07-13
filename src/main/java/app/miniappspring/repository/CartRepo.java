package app.miniappspring.repository;

import app.miniappspring.entity.BagProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<BagProduct,Long> {

    Optional<List<BagProduct>> getAllByUser_UsernameOrderByIdProduct(String username);
    Optional<BagProduct> getByUser_UsernameAndIdProduct(String username, Long idProduct);
    //int countCartProductByUser_UsernameAndAndIdProduct(String username, Long idProduct);
    Optional<BagProduct> getCartProductByUser_UsernameAndAndIdProduct(String username, Long idProduct);
    Optional<Integer> countCartProductByUser_Username(String username);

    void removeByUser_UsernameAndIdProduct(String username, Long idProduct);
}
