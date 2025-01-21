package app.miniappspring.repository;

import app.miniappspring.entity.ProductInBagUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsInBagUserRepo extends JpaRepository<ProductInBagUser,Long> {

    Optional<List<ProductInBagUser>> getAllByUser_UsernameOrderByIdProduct(String username);
    Optional<ProductInBagUser> getByUser_UsernameAndIdProduct(String username, Long idProduct);

    Optional<List<ProductInBagUser>> getBagProductByUser_Username(String username);
    Optional<Integer> countCartProductByUser_Username(String username);

    void removeByUser_UsernameAndIdProduct(String username, Long idProduct);
    Optional<ProductInBagUser> getProductInBagUserByUser_UsernameAndIdProduct(String username, Long idProduct);
    void deleteByUser_UsernameAndIdProduct(String username, Long idProduct);


}
