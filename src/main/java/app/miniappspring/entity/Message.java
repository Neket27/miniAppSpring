package app.miniappspring.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "message")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Schema($schema = "Модель сообщения")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;
    private String tag;


}
