package app.miniappspring.dto.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseGigaChat {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_at")
    private Long expiresAt;
}
