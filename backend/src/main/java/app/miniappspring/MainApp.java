package app.miniappspring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = run(MainApp.class, args);
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        Runnable task = () -> {
//            LocalTime now = LocalTime.now();
//            System.out.println("Текущее время в формате hh:mm:ss: " + now);
//            GigaChatDialog gigaChatDialog =context.getBean("gigaChatDialog",GigaChatDialog.class);
//            System.out.println(gigaChatDialog.getResponse("Раскажи историю"));
        };

//        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

//        scheduler.schedule(() -> {
//            scheduler.shutdown();
//            System.out.println("Задачи завершены.");
//        }, 1000, TimeUnit.SECONDS);
//    }


}
