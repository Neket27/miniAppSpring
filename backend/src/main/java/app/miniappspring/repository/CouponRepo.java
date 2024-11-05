package app.miniappspring.repository;

import app.miniappspring.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface CouponRepo extends JpaRepository<Coupon, Long> {

    Optional<Coupon> findByTitle(String title);
    Boolean existsAllByTitle(String title);
}
