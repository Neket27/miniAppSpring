package app.miniappspring.service.impl;

import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.entity.Feedback;
import app.miniappspring.repository.FeedbackRepo;
import app.miniappspring.service.FeedbackService;
import app.miniappspring.service.ProductService;
import app.miniappspring.utils.jwtToken.mapper.FeedbackMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImp implements FeedbackService {

    private final FeedbackRepo feedbackRepo;
    private final FeedbackMapper feedbackMapper;
    private final ProductService productService;
    @Override
    public FeedbackDto addFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = feedbackMapper.toFeedback(feedbackDto);
        feedback.setProduct(productService.findProduct(feedbackDto.getIdProduct()));
        return feedbackMapper.toFeedbackDto(feedback);
    }
}
