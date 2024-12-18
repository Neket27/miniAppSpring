package app.miniappspring.service.impl;

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
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

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
    private boolean getCountFeedback=false;
    @Override
    public FeedbackDto addFeedback(FeedbackCreateDto feedbackCreateDto) {
        Feedback feedback = feedbackMapper.toFeedback(feedbackCreateDto);
        String username = (String) RequestContextHolder.currentRequestAttributes().getAttribute("username", RequestAttributes.SCOPE_REQUEST);
        feedback.setUser(userService.getByUsername(username));
        feedback.setDate(new Date());
        feedback.setProduct(productService.findProduct(feedbackCreateDto.getIdProduct()));

        int countFeedBack=2;
        if(!getCountFeedback) {
            countFeedBack = feedbackRepo.countFeedbackByProduct_Id(feedbackCreateDto.getIdProduct());
            if(countFeedBack==0)
                countFeedBack=1;
            else
                countFeedBack=2;
        }

        productService.changeRating(feedbackCreateDto.getEvaluation(),feedbackCreateDto.getIdProduct(),countFeedBack);
        return feedbackMapper.toFeedbackDto(feedbackRepo.save(feedback));
    }

    @Override
    public List<FeedbackDto> getFeedbackList(Long idProduct) {
        String username = (String) RequestContextHolder.currentRequestAttributes().getAttribute("username", RequestAttributes.SCOPE_REQUEST);
        Image photoUser = userService.getByUsername(username).getAvatar();
        List<FeedbackDto>feedbackDtoList=feedbackMapper.toFeedbackList(feedbackRepo.getAllByProductId(idProduct).orElse(Collections.emptyList()),photoUser);
        return feedbackDtoList;
    }
}
