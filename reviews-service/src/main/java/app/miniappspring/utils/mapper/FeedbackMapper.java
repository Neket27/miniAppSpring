package app.miniappspring.utils.mapper;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.dto.user.UserDto;
import app.miniappspring.entity.Feedback;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ImageMapper.class})
public abstract class FeedbackMapper {
    @Autowired
    ImageMapper imageMapper;


   public abstract Feedback toFeedback(FeedbackCreateDto feedbackCreateDto);
   public abstract FeedbackDto toFeedbackDto(Feedback feedback);

   public List<FeedbackDto> toFeedbackDtoListAndSetAvatar(List<Feedback> feedbackList, List<UserDto>userDtoList){

        return feedbackList.stream().map(feedback ->{
                    FeedbackDto feedbackDto = this.toFeedbackDto(feedback);
                    if(userDtoList != null) {
                        userDtoList.stream().forEach(userDto->{
                            if(userDto!=null && userDto.id().equals(feedback.getUserId()))
                                feedbackDto.setPhotoUser(imageMapper.toImageDto(userDto.avatar()));
                        });

                    }
                    return feedbackDto;
                }).toList();
    }

}
