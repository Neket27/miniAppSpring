package app.miniappspring.repository;

import app.miniappspring.entity.CategoryProduct;
import app.miniappspring.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Optional<List<Product>> findByNameContainsIgnoreCase(String str);
    Optional<List<Product>> findByCategoryItem_NameContainingIgnoreCase(String str);
    Optional<List<Product>> findByDiscountList_City(String city);
}
