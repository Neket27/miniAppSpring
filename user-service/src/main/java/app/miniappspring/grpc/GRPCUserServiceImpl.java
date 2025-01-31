package app.miniappspring.grpc;

import app.miniappspring.entity.Image;
import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import app.miniappspring.grpc.test.NumbersServiceImpl;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.demo.numbers.*;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class GRPCUserServiceImpl extends UserServerGrpc.UserServerImplBase {
    private static final Logger log = LoggerFactory.getLogger(NumbersServiceImpl.class);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2); // 2 потока для всех задач

    @Override
    public void getUserByUsername(GRPCUsername request, StreamObserver<GRPCUser> responseObserver) {
        log.info("Получен запрос getUserByUsername: {}", request.getUsername());

        scheduler.schedule(() -> {
            try {
                GRPCUser user = getByUsername(request.getUsername());
                if (user == null) {
                    throw new RuntimeException("Пользователь не найден: " + request.getUsername());
                }
                responseObserver.onNext(user);
                responseObserver.onCompleted();
            } catch (Exception e) {
                log.error("Ошибка при обработке запроса getUserByUsername", e);
                responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
            }
        }, 500, TimeUnit.MILLISECONDS);
    }


    @PreDestroy
    public void cleanup() {
        shutdown();
    }

    // 🛑 Метод для корректного завершения сервиса (вызывать при уничтожении GRPCUserServiceImpl)
    public void shutdown() {
        log.info("Shutting down scheduler...");
        scheduler.shutdown();
    }


    private GRPCUser getByUsername(String username) {
//        User user = userService.getByUsername(username);
        User  user =User.builder()
                .id(0L)
                .firstname("Иван")
                .lastname("Иванов")
                .username(username)
                .password("password123")
                .email("ivan@example.com")
                .avatar(new Image()) // Предполагается, что вы создали объект Image
                .roles(Set.of(Role.ROLE_EMPLOYEE)) // Устанавливаем роль
                .build();
//        Image image = user.getAvatar();
        Image image = new Image();
        image.setId(1L);
        image.setName("1");
        image.setBytes(new byte[]{1, 2, 3});
        image.setContentType("image/png");
        image.setUserId(1L);

        GRPCImage grpcImage = GRPCImage.newBuilder()
                .setId(image.getId())
                .setName(image.getName())
                .setBytes(ByteString.copyFrom(image.getBytes()))
                .setContentType(image.getContentType())
                .setUserId(image.getUserId())
                .build();

        GRPCUser.Builder grpcUserBuilder = GRPCUser.newBuilder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setEmail(user.getEmail())
                .setAvatar(grpcImage);

        user.getRoles().forEach(role -> grpcUserBuilder.addRoles(mapRole(role)));



      return  grpcUserBuilder.build();
    }

    private GRPRole mapRole(Role role) {
        return switch (role) {
            case ROLE_USER -> GRPRole.ROLE_USER;
            case ROLE_ADMIN -> GRPRole.ROLE_ADMIN;
            case ROLE_EMPLOYEE -> GRPRole.ROLE_EMPLOYEE;
        };
    }


}