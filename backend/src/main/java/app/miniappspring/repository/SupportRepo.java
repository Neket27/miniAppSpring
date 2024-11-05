package app.miniappspring.repository;

import app.miniappspring.entity.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepo extends JpaRepository<Support, Long> {
}
