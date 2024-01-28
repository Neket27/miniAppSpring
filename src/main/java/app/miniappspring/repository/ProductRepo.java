package app.miniappspring.repository;

import app.miniappspring.entity.CategoryProduct;
import app.miniappspring.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    Optional<Product> getFirstByCategoryProduct(CategoryProduct categoryProduct);
    Optional<List<Product>> findAllByCategoryProduct(CategoryProduct categoryProduct);
    Optional<Integer> countAllByCategoryProduct(CategoryProduct categoryProduct);
}
