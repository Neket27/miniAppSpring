package app.miniappspring.grpc;

import app.miniappspring.dto.user.UserDto;
import app.miniappspring.grpc.streamobserver.ClientStreamObserver;
import app.miniappspring.utils.mapper.UserMapper;
import io.grpc.ManagedChannel;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.demo.numbers.GRPCUsername;
import ru.demo.numbers.UserServerGrpc;

import java.util.concurrent.CountDownLatch;

@Component
@RequiredArgsConstructor
public class UserClient {

    private final ManagedChannel channel;
    private final UserMapper userMapper;
    private static final Logger log = LoggerFactory.getLogger(UserClient.class);

    public UserDto getUser (String username) {
        GRPCUsername grpcUsername = GRPCUsername.newBuilder().setUsername(username).build();
        UserServerGrpc.UserServerStub asyncClient = UserServerGrpc.newStub(channel);

        var latch = new CountDownLatch(1);
        ClientStreamObserver clientStreamObserver =new ClientStreamObserver(latch);
        asyncClient.getUserByUsername(grpcUsername,clientStreamObserver);

        try {
            latch.await();
        }catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

        return userMapper.fromGRPCUser(clientStreamObserver.getUser());

    }

    @PreDestroy
    public void cleanup() {
        log.info("user Client is shutting down...");
        channel.shutdown();
    }
}
