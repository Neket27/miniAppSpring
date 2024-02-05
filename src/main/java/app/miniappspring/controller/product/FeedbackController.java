package app.miniappspring.controller.product;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/add")
    public FeedbackDto addFeedback(@RequestBody FeedbackCreateDto feedbackCreateDto){
       return feedbackService.addFeedback(feedbackCreateDto);
    }

    @GetMapping
    public List<FeedbackDto> getFeedbackList(@RequestParam Long idProduct){
        return feedbackService.getFeedbackList(idProduct);
    }
}
