package app.miniappspring.service.impl;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.entity.Feedback;
import app.miniappspring.entity.Image;
import app.miniappspring.entity.RatingProduct;
import app.miniappspring.repository.FeedbackRepo;
import app.miniappspring.repository.RatingRepo;
import app.miniappspring.service.FeedbackService;
import app.miniappspring.utils.mapper.FeedbackMapper;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImp implements FeedbackService {

    private final FeedbackRepo feedbackRepo;
    private final RatingRepo ratingRepo;
    private final FeedbackMapper feedbackMapper;

    @Override
    @Transactional
    public FeedbackDto addFeedbackGetUpdatingRating(String username, FeedbackCreateDto feedbackCreateDto) {
        Feedback feedback = feedbackMapper.toFeedback(feedbackCreateDto);
        feedback.setUserId(feedbackCreateDto.getUserId());
        feedback.setDate(new Date());
        feedback.setProductId(feedback.getProductId());

        int countFeedBack = feedbackRepo.countFeedbackByproductId(feedbackCreateDto.getIdProduct());

        changeRating(feedbackCreateDto.getEvaluation(), feedbackCreateDto.getIdProduct(), countFeedBack);
        return feedbackMapper.toFeedbackDto(feedbackRepo.save(feedback));
    }

    @Override
    @Transactional
    public List<FeedbackDto> getFeedbackList(String username, Long idProduct) {
        List<Feedback> feedbackList = feedbackRepo.findAllByProductId(idProduct).orElse(Collections.emptyList());
//        List<Image> avatarList = feedbackList.stream().map(feedback -> {
//            return feedback.getUser().getAvatar();
//        }).toList();
        List<Image> avatarList = null;
        return feedbackMapper.toFeedbackDtoListAndSetAvatar(feedbackList, avatarList);
    }


    private float changeRating(float evaluation, Long idProduct, int countFeedback) {
        RatingProduct ratingProduct = ratingRepo.getByProductId(idProduct).orElseThrow(() -> new NotFoundException("No such product"));
        float rating = ((ratingProduct.getEvaluation() * countFeedback) + evaluation) / (countFeedback + 1);
        ratingProduct.setEvaluation(rating);
        ratingRepo.save(ratingProduct);
        return rating;
    }
}
