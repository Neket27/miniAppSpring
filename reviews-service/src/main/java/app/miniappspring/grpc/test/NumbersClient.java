package app.miniappspring.grpc.test;

import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.demo.numbers.NumberRequest;
import ru.demo.numbers.NumbersServiceGrpc;

import java.util.concurrent.CountDownLatch;

public class NumbersClient {
    private static final Logger log = LoggerFactory.getLogger(NumbersClient.class);

    public static void main(String[] args) throws InterruptedException {
        log.info("numbers Client is starting...");

        var managedChannel = ManagedChannelBuilder.forAddress("localhost",8980 )
                .usePlaintext()
                .build();

        var asyncClient = NumbersServiceGrpc.newStub(managedChannel);

        var latch = new CountDownLatch(1);
        asyncClient.getNumber(makeNumberRequest(), new ClientStreamObserver2(latch));

        latch.await();

        log.info("numbers Client is shutting down...");
        managedChannel.shutdown();
    }

    private static NumberRequest makeNumberRequest() {
        return NumberRequest.newBuilder()
                .setFirstValue(1)
                .setLastValue(10)
                .build();
    }
}
