package app.miniappspring.grpc.server;

import app.miniappspring.grpc.GRPCUserServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class GrpcServerConfig {

    private static final Logger log = LoggerFactory.getLogger(GrpcServerConfig.class);

    @Bean("GrpcServer")
    public Server createGrpcServer() {
        Server server = ServerBuilder.forPort(8980)
                .addService(new GRPCUserServiceImpl())
                .build();
        try {
            server.start();
            System.out.println("Server started, listening on " + server.getPort());
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return server;
    }

}
