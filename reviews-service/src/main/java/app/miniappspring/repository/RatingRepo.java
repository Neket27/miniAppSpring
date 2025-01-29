package app.miniappspring.repository;

import app.miniappspring.entity.RatingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepo extends JpaRepository<RatingProduct, Long> {

    Optional<RatingProduct> getByProductId(Long productId);
}
