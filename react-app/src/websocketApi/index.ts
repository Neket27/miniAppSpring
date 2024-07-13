// import { Client } from '@stomp/stompjs';
// import SockJS from 'sockjs-client';
//
// class WebSocketApi {
//     private stompClient: Client;
//     private firstTopicMessages: any[];
//     private secondTopicMessages: any[];
//     constructor() {
//         this.firstTopicMessages = [];
//         this.secondTopicMessages = [];
//         this.connect();
//     }
//
//     connect() {
//         this.stompClient = new Client({
//             webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
//             onConnect: (frame) => {
//                 console.log('Connected: ' + frame);
//                 this.subscribeToTopics();
//             },
//             onStompError: (frame) => {
//                 console.error('Broker reported error: ' + frame.headers['message']);
//                 console.error('Additional details: ' + frame.body);
//             },
//         });
//
//         this.stompClient.activate();
//     }
//
//     subscribeToFirstTopic(callback:any) {
//         this.stompClient.subscribe('/shoppingCartCountProduct/public', (message) => {
//             console.log('Received message from firstTopic: ' + message.body);
//             this.firstTopicMessages.push(message.body);
//             if (callback) {
//                 callback(message);
//             }
//         });
//     }
//
//     subscribeToSecondTopic(callback:any) {
//         this.stompClient.subscribe('/shoppingCart/public', (message) => {
//             console.log('Received message from secondTopic: ' + message.body);
//             this.secondTopicMessages.push(message.body);
//             if (callback) {
//                 callback(message.body);
//             }
//         });
//     }
//
//     subscribeToTopics() {
//         this.subscribeToFirstTopic(null);
//         this.subscribeToSecondTopic(null);
//     }
//
//     sendMessage(destination:string, message:string) {
//         if (this.stompClient && this.stompClient.connected) {
//             this.stompClient.publish({
//                 destination: destination,
//                 body: message,
//             });
//         }
//     }
//
//     getFirstTopicMessages() {
//         return this.firstTopicMessages;
//     }
//
//     getSecondTopicMessages() {
//         return this.secondTopicMessages;
//     }
// }
//
// const webSocketApi = new WebSocketApi();
// export default webSocketApi;
