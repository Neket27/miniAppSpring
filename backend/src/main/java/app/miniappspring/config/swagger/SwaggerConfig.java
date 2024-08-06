package app.miniappspring.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI configOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("MiniApp")
                        .description("Example project")
                        .version("1.0"));
    }
}