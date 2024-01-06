package app.miniappspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DtoCurrentUser {
    private String username="Пройдите авторизацию";
}
