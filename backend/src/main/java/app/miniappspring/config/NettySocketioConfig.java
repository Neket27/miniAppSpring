//package app.miniappspring.config;
//
//
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class NettySocketioConfig {
//    /**
//     * Netty-Socketio server
//     */
//    @Bean
//    public SocketIOServer socketIOServer() {
//        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
//        config.setHostname("localhost");
//        config.setPort(9094);
//
//        SocketIOServer server = new SocketIOServer(config);
//        return server;
//    }
//
//    /**
//     * Used to scan Netty-socketio's annotation, such as @ onconnect, @ onvent
//     */
//    @Bean
//    public SpringAnnotationScanner springAnnotationScanner() {
//        return new SpringAnnotationScanner(socketIOServer());
//    }
//}
//
