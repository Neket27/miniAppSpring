package app.miniappspring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableCaching
public class ReviewsService {
    public static void main(String[] args){
        ConfigurableApplicationContext context = run(ReviewsService.class, args);
    }

}
