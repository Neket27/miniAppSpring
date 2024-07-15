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

//    Optional<Product> getFirstByCategoryProduct(CategoryProduct categoryProduct);
//    Optional<List<Product>> findAllByCategoryProduct(CategoryProduct categoryProduct);
//    Optional<Integer> countAllByCategoryProduct(CategoryProduct categoryProduct);

//    @Query(value = "SELECT * FROM product WHERE LOWER(category_product) LIKE LOWER(CONCAT('%', :searchString, '%'))", nativeQuery = true)
//    Optional<List<Product>> findByCategoryProductRussianValueContainingIgnoreCase(@Param("searchString") String searchString);

//    long countByCategoryProduct(CategoryProduct categoryProduct);

    Optional<List<Product>> findByNameContainsIgnoreCase(String str);
    Optional<List<Product>> findByCategoryProduct_StringValueCategoryContainingIgnoreCase(String str);



}
