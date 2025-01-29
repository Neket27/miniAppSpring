package app.miniappspring.controller;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/add")
    public FeedbackDto addFeedback(@RequestBody FeedbackCreateDto feedbackCreateDto){
       return feedbackService.addFeedbackGetUpdatingRating(getUsernameFromAttributesRequest(),feedbackCreateDto);
    }

    @GetMapping("/list")
    public List<FeedbackDto> getFeedbackList(@RequestParam Long idProduct){
        return feedbackService.getFeedbackList(getUsernameFromAttributesRequest(), idProduct);
    }

    private String getUsernameFromAttributesRequest(){
        return  (String) RequestContextHolder.currentRequestAttributes().getAttribute("username", RequestAttributes.SCOPE_REQUEST);
    }
}
