package app.miniappspring.repository;

import app.miniappspring.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<Image,Long> {

    List<Image> findAllByProduct_Id(Long id);
    void  deleteAllByProduct_Id(Long id);

}
