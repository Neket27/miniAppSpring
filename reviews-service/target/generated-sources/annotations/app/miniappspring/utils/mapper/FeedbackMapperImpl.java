package app.miniappspring.utils.mapper;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.entity.Feedback;
import java.time.ZoneOffset;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-31T16:15:33+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class FeedbackMapperImpl extends FeedbackMapper {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public Feedback toFeedback(FeedbackCreateDto feedbackCreateDto) {
        if ( feedbackCreateDto == null ) {
            return null;
        }

        Feedback feedback = new Feedback();

        feedback.setNameUser( feedbackCreateDto.getNameUser() );
        feedback.setEmail( feedbackCreateDto.getEmail() );
        feedback.setMessage( feedbackCreateDto.getMessage() );
        feedback.setEvaluation( feedbackCreateDto.getEvaluation() );
        feedback.setUserId( feedbackCreateDto.getUserId() );
        feedback.setProductId( feedbackCreateDto.getProductId() );
        feedback.setImageList( imageMapper.toImageListFromCreate( feedbackCreateDto.getImageList() ) );

        return feedback;
    }

    @Override
    public FeedbackDto toFeedbackDto(Feedback feedback) {
        if ( feedback == null ) {
            return null;
        }

        FeedbackDto feedbackDto = new FeedbackDto();

        feedbackDto.setId( feedback.getId() );
        feedbackDto.setNameUser( feedback.getNameUser() );
        feedbackDto.setEmail( feedback.getEmail() );
        feedbackDto.setMessage( feedback.getMessage() );
        feedbackDto.setEvaluation( feedback.getEvaluation() );
        feedbackDto.setImageList( imageMapper.toImageDtoList( feedback.getImageList() ) );
        if ( feedback.getDate() != null ) {
            feedbackDto.setDate( Date.from( feedback.getDate().toInstant( ZoneOffset.UTC ) ) );
        }

        return feedbackDto;
    }
}
