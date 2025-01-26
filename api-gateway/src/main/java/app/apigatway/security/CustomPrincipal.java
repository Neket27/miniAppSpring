package app.apigatway.security;

import lombok.*;

import java.security.Principal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CustomPrincipal implements Principal {
    private Long id;
    private String name;

    @Override
    public String getName() {
        return name;
    }
}
