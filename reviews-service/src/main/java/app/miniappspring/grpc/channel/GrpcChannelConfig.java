package app.miniappspring.grpc.channel;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcChannelConfig {

    @Bean
    public ManagedChannel createChannel(){
        return ManagedChannelBuilder
                .forTarget("localhost:8980")
                .usePlaintext()
                .build();
    }
}
