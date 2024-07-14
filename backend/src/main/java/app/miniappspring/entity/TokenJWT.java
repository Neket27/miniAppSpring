package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "token_jwt")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class TokenJWT {
    @Id
    private Long userId;
    private String refreshToken;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;
}
