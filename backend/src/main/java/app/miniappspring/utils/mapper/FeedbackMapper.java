package app.miniappspring.utils.mapper;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.entity.Feedback;
import app.miniappspring.entity.Image;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ImageMapper.class})
public abstract class FeedbackMapper {
    @Autowired
    ImageMapper imageMapper;


   public abstract Feedback toFeedback(FeedbackCreateDto feedbackCreateDto);
   public abstract FeedbackDto toFeedbackDto(Feedback feedback);

   public List<FeedbackDto> toFeedbackList(List<Feedback> feedbackList, Image photoUser){

        return feedbackList.stream().map(feedback ->{
                    FeedbackDto feedbackDto = this.toFeedbackDto(feedback);
                    if(photoUser != null)
                        feedbackDto.setPhotoUser(imageMapper.toImageDto(photoUser));
                    return feedbackDto;
                }).toList();
    }

}
