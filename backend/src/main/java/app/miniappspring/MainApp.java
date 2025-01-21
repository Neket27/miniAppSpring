package app.miniappspring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableCaching
public class MainApp {
    public static void main(String[] args) throws IllegalAccessException {
        ConfigurableApplicationContext context = run(MainApp.class, args);

    }

}
