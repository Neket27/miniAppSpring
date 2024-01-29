package app.miniappspring.repository;

import app.miniappspring.entity.Category;
import app.miniappspring.entity.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<CategoryProduct,Long> {

    Optional<List<CategoryProduct>> findAllByStringValueCategoryContainingIgnoreCase(String str);
    int countByCategory(Category category);
}
