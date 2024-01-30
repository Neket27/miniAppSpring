package app.miniappspring.service;

import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.entity.Feedback;

public interface FeedbackService {
    FeedbackDto addFeedback(FeedbackDto feedbackDto);
}
