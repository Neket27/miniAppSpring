package app.miniappspring.controller.product;

import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public FeedbackDto addFeedback(@RequestBody FeedbackDto feedbackDto){
       return feedbackService.addFeedback(feedbackDto);
    }
}
