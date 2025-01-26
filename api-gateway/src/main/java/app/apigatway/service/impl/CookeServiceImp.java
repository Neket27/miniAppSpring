package app.apigatway.service.impl;

import app.apigatway.dto.cooke.CreateCookeDto;
import app.apigatway.service.CookeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Service;
//import org.springframework.web.server.ServerHttpResponse;

@Service
@RequiredArgsConstructor
public class CookeServiceImp implements CookeService {

    @Override
    public ResponseCookie createCooke(CreateCookeDto createCookeDto) { // Return ResponseCookie
        ResponseCookie cookie = ResponseCookie.from(createCookeDto.getKey(), createCookeDto.getData())
                .path(createCookeDto.getPath())
                .maxAge(createCookeDto.getTimeLiveCooke())
                .build();
        return cookie;
    }
}
