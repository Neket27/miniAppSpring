//package app.miniappspring.config;
//
//import app.miniappspring.filter.jwt.JwtAuthenticationFilter;
//import app.miniappspring.repository.DeliveryDataUserRepo;
//import app.miniappspring.repository.UserRepo;
//import app.miniappspring.service.UserService;
//import app.miniappspring.service.impl.JWTServiceImpl;
//import app.miniappspring.service.impl.UserServiceImpl;
//import app.miniappspring.utils.jwtToken.mapper.DeliveryMapper;
//import app.miniappspring.utils.jwtToken.mapper.ImageMapper;
//import app.miniappspring.utils.jwtToken.mapper.UserMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class UserServiceConfig {
//
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Bean
//    public UserService userService(UserRepo userRepo,
//                                   UserMapper userMapper,
//                                   ImageMapper imageMapper,
//                                   JWTServiceImpl jwtService,
//                                   DeliveryDataUserRepo deliveryDataUserRepo,
//                                   DeliveryMapper deliveryMapper) {
//        String username = jwtAuthenticationFilter.getUsername();
//        return new UserServiceImpl(username, userRepo, userMapper, imageMapper, jwtService, deliveryDataUserRepo, deliveryMapper);
//    }
//}
