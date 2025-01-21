package app.miniappspring.service.impl.feedback;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AIFeedbackService {

    private final FeedbackService feedbackService;

    @Bean
    @Description("Create feedback")
    public Function<RequestOnCreateFeedback, FeedbackDto> createFeedback() {
        return requestOnCreateFeedback-> feedbackService.addFeedback(requestOnCreateFeedback.username(), requestOnCreateFeedback.feedbackCreateDto());
    }

    @Bean
    @Description("Get Feedback")
    public Function<RequestOnGetListFeedback, List<FeedbackDto>> getFeedback() {
        return requestOnGetListFeedback -> feedbackService.getFeedbackList(requestOnGetListFeedback.username(), requestOnGetListFeedback.idProduct());
    }
}

record RequestOnCreateFeedback(String username,FeedbackCreateDto feedbackCreateDto) {}
record RequestOnGetListFeedback(String username,Long idProduct) {}
