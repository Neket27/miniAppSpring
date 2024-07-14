package app.miniappspring.repository;

import app.miniappspring.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageProductRepo extends JpaRepository<Image,Long> {

}
