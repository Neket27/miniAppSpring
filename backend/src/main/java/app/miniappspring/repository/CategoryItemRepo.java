package app.miniappspring.repository;

import app.miniappspring.entity.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryItemRepo extends JpaRepository<CategoryItem, Long> {

    int countByName(String name);

    Optional<CategoryItem>findByName(String name);
}
