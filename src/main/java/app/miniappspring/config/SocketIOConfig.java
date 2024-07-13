//package app.miniappspring.config;
//
//import com.corundumstudio.socketio.AckRequest;
//import com.corundumstudio.socketio.SocketIOClient;
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.annotation.OnConnect;
//import com.corundumstudio.socketio.annotation.OnDisconnect;
//import com.corundumstudio.socketio.annotation.OnEvent;
//import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
//import org.h2.util.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//@org.springframework.context.annotation.Configuration
//public class SocketIOConfig {
//    @Autowired
//    private SocketIOServer socketIoServer;
//
//    @Bean
//    public SocketIOServer socketIOServer() {
//        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
//        config.setHostname("localhost");
//        config.setPort(8081);// Sitting Socket port
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
//
//
//    public static ConcurrentMap<String, SocketIOClient> socketIOClientMap = new ConcurrentHashMap<>();
//
//    /**
//     * Trigger when the client is connected
//     * @param client
//     */
//    @OnConnect
//    public void onConnect(SocketIOClient client) {
//        //
//        String mac = client.getHandshakeData().getSingleUrlParam("mac");
//        // Store Socketioclient for sending a message
//        socketIOClientMap.put(mac, client);
//        // Remove the message to the client via client.sendevent
//        client.sendEvent("message", "onConnect back");
//        System.out.println("Client:" + client.getSessionId() + "Connected, Mac =" + mac);
//    }
//
//    @OnDisconnect
//    public void onDisconnect(SocketIOClient client) {
//        SocketIOClient socketIOClient = socketIOClientMap.get(client.getHandshakeData().getSingleUrlParam("mac"));
//        if (null != socketIOClient){
//            socketIOClientMap.remove(socketIOClient);
//        }
//        System.out.println("Client:" + client.getSessionId() + "Disconnect");
//    }
//
//    @OnEvent(value = "messageevent")
//    public void onEvent(SocketIOClient client, AckRequest request, String data) {
//        System.out.println("Send a message:" + data);
//        // Return news
//        client.sendEvent("messageevent", "I am the information sent by the server ==" + data);
//        // broadcast message
//        sendBroadcast();
//    }
//
//    @OnEvent(value = "messageevent2")
//    public void messageevent2(SocketIOClient client,  JSONObject data) {
//        System.out.println("Send a message:" + data);
//        // Return news
//        client.sendEvent("messageevent2", "I am the information sent by the server ==" + data);
//    }
//
//    public void sendBroadcast() {
//        for (SocketIOClient client : socketIOClientMap.values()) {
//            if (client.isChannelOpen()) {
//                client.sendEvent("Broadcast", "current time", System.currentTimeMillis());
//            }
//        }
//    }
//
////    @Bean
////    public SocketIOServer socketIOServer() {
////        Configuration config = new Configuration();
////        config.setHostname("localhost");
////        config.setPort(8081);
////
////        final SocketIOServer server = new SocketIOServer(config);
////
////        server.addConnectListener(new ConnectListener() {
////            @Override
////            public void onConnect(com.corundumstudio.socketio.SocketIOClient client) {
////                System.out.println("Client connected: " + client.getSessionId());
////            }
////        });
////
////        server.addDisconnectListener(new DisconnectListener() {
////            @Override
////            public void onDisconnect(com.corundumstudio.socketio.SocketIOClient client) {
////                System.out.println("Client disconnected: " + client.getSessionId());
////            }
////        });
////
////        server.addEventListener("message", String.class, new DataListener<String>() {
////            @Override
////            public void onData(com.corundumstudio.socketio.SocketIOClient client, String data, AckRequest ackSender) {
////                System.out.println("Received message from client: " + data);
////                // Вы можете отправить ответ обратно клиенту, если это необходимо
////               //  client.sendEvent("message", data);
////            }
////        });
////
////        return server;
////    }
//}
