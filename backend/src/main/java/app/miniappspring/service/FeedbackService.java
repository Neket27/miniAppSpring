package app.miniappspring.service;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;

import java.util.List;

public interface FeedbackService {
    FeedbackDto addFeedback(String username, FeedbackCreateDto feedbackCreateDto);

    List<FeedbackDto> getFeedbackList(String username, Long idProduct);
}
