package app.miniappspring.service.impl.feedback;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.entity.Feedback;
import app.miniappspring.entity.Image;
import app.miniappspring.repository.FeedbackRepo;
import app.miniappspring.service.FeedbackService;
import app.miniappspring.service.ProductService;
import app.miniappspring.service.UserService;
import app.miniappspring.utils.mapper.FeedbackMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImp implements FeedbackService {

    private final FeedbackRepo feedbackRepo;
    private final FeedbackMapper feedbackMapper;
    private final ProductService productService;
    private final UserService userService;

    @Override
    public FeedbackDto addFeedback(String username, FeedbackCreateDto feedbackCreateDto) {
        Feedback feedback = feedbackMapper.toFeedback(feedbackCreateDto);
        feedback.setUser(userService.getByUsername(username));
        feedback.setDate(new Date());
        feedback.setProduct(productService.findProduct(feedbackCreateDto.getIdProduct()));

        int countFeedBack = feedbackRepo.countFeedbackByProduct_Id(feedbackCreateDto.getIdProduct());

        productService.changeRating(feedbackCreateDto.getEvaluation(),feedbackCreateDto.getIdProduct(),countFeedBack);
        return feedbackMapper.toFeedbackDto(feedbackRepo.save(feedback));
    }

    @Override
    public List<FeedbackDto> getFeedbackList(String username, Long idProduct) {
        List<Feedback> feedbackList  = feedbackRepo.findAllByProduct_Id(idProduct).orElse(Collections.emptyList());
        List<Image> avatarList = feedbackList.stream().map(feedback -> {
            return feedback.getUser().getAvatar();
        }).toList();
        return feedbackMapper.toFeedbackDtoListAndSetAvatar(feedbackList,avatarList);
    }
}
