package app.miniappspring.grpc.streamobserver;


import io.grpc.stub.StreamObserver;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.demo.numbers.GRPCUser;

import java.util.concurrent.CountDownLatch;

@Getter
public class ClientStreamObserver implements StreamObserver<GRPCUser> {
    private static final Logger log = LoggerFactory.getLogger(ClientStreamObserver.class);
    private final CountDownLatch latch;
    private volatile GRPCUser user;

    public ClientStreamObserver(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onNext(GRPCUser user) {
        log.info("Received user: {}", user);
        this.user = user; // Сохраняем ответ
    }

    @Override
    public void onError(Throwable t) {
        log.error("got error", t);
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        log.info("request completed");
        latch.countDown();
    }


}
