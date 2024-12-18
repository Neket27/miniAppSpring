package app.miniappspring.repository;

import app.miniappspring.entity.DeliveryDataUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryDataUserRepo extends JpaRepository<DeliveryDataUser, Long> {

    Optional<DeliveryDataUser> findByUser_Username(String username);
}
