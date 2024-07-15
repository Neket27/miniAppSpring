package app.miniappspring.service;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    FeedbackDto addFeedback(FeedbackCreateDto feedbackCreateDto);

    List<FeedbackDto> getFeedbackList(Long idProduct);
}
