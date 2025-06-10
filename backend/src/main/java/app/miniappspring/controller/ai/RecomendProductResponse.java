package app.miniappspring.controller.ai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RecomendProductResponse {

    private List<Long> productIds;
    private String message;
}
