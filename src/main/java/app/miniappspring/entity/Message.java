package app.miniappspring.entity;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
//@Table(name = "EMPLOYEE")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@ApiModel("Модель сотрудника")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;
    private String tag;


}
