package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.entity.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    Feedback toFeedback(FeedbackDto feedbackDto);
    @Mapping(source = "product.id",target = "idProduct")
    FeedbackDto toFeedbackDto(Feedback feedback);
}
