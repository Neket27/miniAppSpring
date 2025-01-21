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

   public List<FeedbackDto> toFeedbackDtoListAndSetAvatar(List<Feedback> feedbackList,List<Image>avatarList){

        return feedbackList.stream().map(feedback ->{
                    FeedbackDto feedbackDto = this.toFeedbackDto(feedback);
                    if(avatarList != null) {
                        avatarList.stream().forEach(avatar->{
                            if(avatar!=null && avatar.getUser().getId().equals(feedback.getUser().getId()))
                                feedbackDto.setPhotoUser(imageMapper.toImageDto(avatar));
                        });

                    }
                    return feedbackDto;
                }).toList();
    }

}
