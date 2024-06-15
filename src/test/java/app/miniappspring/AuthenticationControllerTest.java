package app.miniappspring;


import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.dto.jwtToken.JwtAuthenticationResponse;
import app.miniappspring.dto.jwtToken.SignUpRequest;
import app.miniappspring.dto.jwtToken.SigninRequest;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.service.UserService;
import app.miniappspring.utils.jwtToken.EncoderPassword;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationControllerTest {

    @Autowired
    private WebTestClient webClient;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    UserService userService;
    private SignUpRequest signUpRequest;
    private CreateUserArgument createUserArgument;
    private SigninRequest signinRequest;
    private JwtAuthenticationResponse token;
    @BeforeEach
    private void setData() throws IOException {
        userService.remove("testUsername");
        signUpRequest= data("signUpRequest.json", SignUpRequest.class);
        createUserArgument= data("signUpRequest.json", CreateUserArgument.class);
        signinRequest= data("signUpRequest.json", SigninRequest.class);
        token= data("tokenAuthenticatedUser.json", JwtAuthenticationResponse.class);
    }

    private <T> T data(String file,Class<T> fileClass) throws IOException {
        File resource =new ClassPathResource(file).getFile();
        return objectMapper.readValue(new String(Files.readAllBytes(resource.toPath())),fileClass);
    }

    public MultiValueMap<String, HttpEntity<?>> fromFile(File file) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", new FileSystemResource(file));
        return builder.build();
    }


    @Test
    public void signUpTest() throws IOException {
        //Arrange

        //Act
         CreateUserDto user=webClient.post()
                .uri("/api/v1/auth/singup")
                .bodyValue(signUpRequest)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(CreateUserDto.class)
                .returnResult()
                .getResponseBody();

        userService.remove("testUsername");

        //Assert
        assertEquals(signUpRequest.getUsername(),user.getUsername());
        assertTrue(EncoderPassword.equalsPasswords(signUpRequest.getPassword(),user.getPassword()));
    }

    @Test
    public void signin() throws IOException {
        //Arrange
        String uploadFileyPath = "src/test/resources/tokenAuthenticatedUser.json";

        //Act
        userService.addUser(createUserArgument);

        JwtAuthenticationResponse jwtAuthenticationResponse = webClient.post()
                .uri("/api/v1/auth/signin")
                .bodyValue(signinRequest)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(JwtAuthenticationResponse.class)
                .returnResult()
                .getResponseBody();


        objectMapper.writeValue(new File(uploadFileyPath),jwtAuthenticationResponse);


        //Assert

    }

//    @Test
//    public void authorizedUser() throws IOException {
//        //Arrange
//        signin();
//
//        //Act
//        webClient.get()
//                .uri("/api/v1/user")
//                .headers(h -> h.setBearerAuth(token.getToken()))
//                .exchange()
//                .expectStatus()
//                .isOk();
//
//        userService.remove(createUserArgument.getUsername());
//
//        //Assert
//
//    }

}
