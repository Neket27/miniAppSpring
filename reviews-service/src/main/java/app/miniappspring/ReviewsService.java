package app.miniappspring;

import app.miniappspring.dto.feedback.FeedbackDto;
import app.miniappspring.grpc.UserClient;
import app.miniappspring.service.FeedbackService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableCaching
public class ReviewsService {
    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext context = run(ReviewsService.class, args);
        UserClient userClient = context.getBean("userClient", UserClient.class);
        FeedbackService feedbackService = context.getBean("feedbackService",FeedbackService.class);

        List<FeedbackDto> feedbackDtoList =  feedbackService.getFeedbackList("nikita",1L);
        feedbackDtoList.forEach(System.out::println);
//        UserDto userDto = userClient.getUser("nikita");

//        System.out.println("user MY =   "+ userDto);
//        userClient.getUser("nikita");
//        userClient.getUser("nikita");
    }

}
