package krugers.microservicio.gateway.gatewaymicroservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {
    private String token;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
