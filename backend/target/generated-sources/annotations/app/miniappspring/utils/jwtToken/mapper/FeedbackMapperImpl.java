package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.entity.Feedback;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-06T00:17:19+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class FeedbackMapperImpl extends FeedbackMapper {

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
        List<byte[]> list = feedbackCreateDto.getImageList();
        if ( list != null ) {
            feedback.setImageList( new ArrayList<byte[]>( list ) );
        }

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
        List<byte[]> list = feedback.getImageList();
        if ( list != null ) {
            feedbackDto.setImageList( new ArrayList<byte[]>( list ) );
        }
        feedbackDto.setDate( feedback.getDate() );

        return feedbackDto;
    }
}
