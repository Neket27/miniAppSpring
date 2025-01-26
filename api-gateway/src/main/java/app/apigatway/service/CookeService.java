package app.apigatway.service;

import app.apigatway.dto.cooke.CreateCookeDto;
import org.springframework.http.ResponseCookie;

public interface CookeService {
    ResponseCookie createCooke(CreateCookeDto createCookeDto);
}
