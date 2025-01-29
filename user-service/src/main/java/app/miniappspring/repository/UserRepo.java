package app.miniappspring.repository;

import app.miniappspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String userName);
    Optional<Boolean> deleteUserByUsername(String username);

    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);

}
