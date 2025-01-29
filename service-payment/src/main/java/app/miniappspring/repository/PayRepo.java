package app.miniappspring.repository;

import app.miniappspring.entity.PayData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepo extends JpaRepository<PayData, Long> {

}
