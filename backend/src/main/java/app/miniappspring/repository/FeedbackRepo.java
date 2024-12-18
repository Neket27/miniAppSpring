package app.miniappspring.repository;

import app.miniappspring.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback,Long> {
    Optional<List<Feedback>> getAllByProductId(Long idProduct);
    int countFeedbackByProduct_Id(Long idProduct);
}
