package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.dto.feedback.FeedbackCreateDto;
import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.entity.Feedback;
import app.miniappspring.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-19T01:01:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class FeedbackMapperImpl implements FeedbackMapper {

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

        feedbackDto.setIdProduct( feedbackProductId( feedback ) );
        feedbackDto.setId( feedback.getId() );
        feedbackDto.setNameUser( feedback.getNameUser() );
        feedbackDto.setEmail( feedback.getEmail() );
        feedbackDto.setMessage( feedback.getMessage() );
        feedbackDto.setEvaluation( feedback.getEvaluation() );
        List<byte[]> list = feedback.getImageList();
        if ( list != null ) {
            feedbackDto.setImageList( new ArrayList<byte[]>( list ) );
        }

        return feedbackDto;
    }

    private Long feedbackProductId(Feedback feedback) {
        if ( feedback == null ) {
            return null;
        }
        Product product = feedback.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
