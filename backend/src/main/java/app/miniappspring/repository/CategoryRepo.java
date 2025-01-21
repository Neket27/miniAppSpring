package app.miniappspring.repository;

import app.miniappspring.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    int countByName(String name);

    Optional<Category>findByName(String name);
}
