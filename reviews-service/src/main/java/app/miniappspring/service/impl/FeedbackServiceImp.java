package app.miniappspring.service.impl;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.dto.user.UserDto;
import app.miniappspring.entity.Feedback;
import app.miniappspring.entity.Image;
import app.miniappspring.entity.RatingProduct;
import app.miniappspring.grpc.UserClient;
import app.miniappspring.repository.FeedbackRepo;
import app.miniappspring.repository.RatingRepo;
import app.miniappspring.service.FeedbackService;
import app.miniappspring.utils.mapper.FeedbackMapper;
import app.miniappspring.utils.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service(value = "feedbackService")
@RequiredArgsConstructor
public class FeedbackServiceImp implements FeedbackService {

    private final FeedbackRepo feedbackRepo;
    private final RatingRepo ratingRepo;
    private final FeedbackMapper feedbackMapper;
    private final UserClient userClient;
    private final ImageMapper imageMapper;

    @Override
    @Transactional
    public FeedbackDto addFeedbackGetUpdatingRating(String username, FeedbackCreateDto feedbackCreateDto) {

        Feedback feedback = feedbackMapper.toFeedback(feedbackCreateDto);
        feedback.setUserId(feedbackCreateDto.getUserId());
        feedback.setDate(LocalDateTime.now());

        int countFeedBack = feedbackRepo.countFeedbackByproductId(feedback.getProductId());
        changeRating(feedbackCreateDto.getEvaluation(), feedback.getProductId(), countFeedBack);

        List<Image> images = imageMapper.toImageListFromCreate(feedbackCreateDto.getImageList());
        images.forEach(image -> image.setFeedback(feedback));

        feedback.setImageList(images);

        return feedbackMapper.toFeedbackDto(feedbackRepo.save(feedback));
    }


    @Override
    @Transactional
    public List<FeedbackDto> getFeedbackList(String username, Long idProduct) {
        List<Feedback> feedbackList = feedbackRepo.findAllByProductId(idProduct).orElse(Collections.emptyList());
        if(feedbackList.isEmpty())
            return Collections.emptyList();

        List<UserDto> userDtoList = feedbackList.stream().map(feedback -> {
           UserDto userDto =userClient.getUser(feedback.getNameUser());
           return userDto;
        }).toList();

        return feedbackMapper.toFeedbackDtoListAndSetAvatar(feedbackList, userDtoList);
    }

    private float changeRating(float evaluation, Long idProduct, int countFeedback) {
        return ratingRepo.getByProductId(idProduct)
                .map(ratingProduct -> {
                    float newRating = ((ratingProduct.getEvaluation() * countFeedback) + evaluation) / (countFeedback + 1);
                    ratingProduct.setEvaluation(newRating);
                    ratingRepo.save(ratingProduct);
                    return newRating;
                })
                .orElseGet(() -> {
                    RatingProduct newRating = RatingProduct.builder()
                            .evaluation(evaluation)
                            .productId(idProduct)
                            .build();
                    ratingRepo.save(newRating);
                    return evaluation;
                });
    }

}
