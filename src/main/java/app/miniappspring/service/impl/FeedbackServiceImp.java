package app.miniappspring.service.impl;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.entity.Feedback;
import app.miniappspring.repository.FeedbackRepo;
import app.miniappspring.service.FeedbackService;
import app.miniappspring.service.ProductService;
import app.miniappspring.utils.jwtToken.mapper.FeedbackMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImp implements FeedbackService {

    private final FeedbackRepo feedbackRepo;
    private final FeedbackMapper feedbackMapper;
    private final ProductService productService;
    @Override
    public FeedbackDto addFeedback(FeedbackCreateDto feedbackCreateDto) {
        Feedback feedback = feedbackMapper.toFeedback(feedbackCreateDto);
        feedback.setProduct(productService.findProduct(feedbackCreateDto.getIdProduct()));
        return feedbackMapper.toFeedbackDto(feedbackRepo.save(feedback));
    }

    @Override
    public List<FeedbackDto> getFeedbackList(Long idProduct) {
        return feedbackMapper.toFeedbackList(feedbackRepo.getAllByProductId(idProduct).orElse(Collections.emptyList()));
    }
}
