package app.miniappspring.repository;

import app.miniappspring.entity.TokenJWT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<TokenJWT,Long> {

}
