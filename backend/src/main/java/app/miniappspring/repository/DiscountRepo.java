package app.miniappspring.repository;

import app.miniappspring.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepo extends JpaRepository<Discount, Long> {

    Optional<Discount> findDiscountByProductList_Id(Long productId);
    Optional<Discount> findByNameAndCity(String name, String city);
}

