package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.entity.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    Feedback toFeedback(FeedbackCreateDto feedbackCreateDto);
    @Mapping(source = "product.id",target = "idProduct")
    FeedbackDto toFeedbackDto(Feedback feedback);

    default List<FeedbackDto> toFeedbackList(List<Feedback> feedbackList){
        return feedbackList.stream().map(this::toFeedbackDto).toList();
    }
}
