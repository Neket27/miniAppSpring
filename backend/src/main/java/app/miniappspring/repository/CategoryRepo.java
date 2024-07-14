package app.miniappspring.repository;

import app.miniappspring.entity.Category;
import app.miniappspring.entity.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepo extends JpaRepository<CategoryProduct,Long> {

    Optional<Set<CategoryProduct>> findAllByStringValueCategoryContainingIgnoreCase(String str);
    int countByCategory(Category category);
}
