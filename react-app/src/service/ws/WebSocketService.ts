// src/services/WebSocketService.js
import { Client, Message } from '@stomp/stompjs';
import SockJS from "sockjs-client";
import {useContext} from "react";
import {Context, State} from "../../main";
const URL = import.meta.env.VITE_URL;



class WebSocketService {
    private stompClient: Client | null;
    private accessToken: string | null;
    private context:State = useContext(Context)

    constructor() {
        this.stompClient = null;
        this.accessToken = localStorage.getItem('accessToken');
    }


    public connect(destination: string, callback: (message: Message) => void) {
        // this.context.stompClient.brokerURL='ws://localhost:8080/ws';
        this.context.stompClient.webSocketFactory = () => new SockJS(`${URL}/ws`);
        this.context.stompClient.onConnect = () => this.onConnected(destination,callback)




        this.context.stompClient.activate();
        console.log("client con1= "+this.context.stompClient.connected);
        return this.context.stompClient;
    }

    public onConnected =(destination: string, callback: (message: Message) => void) => {
        this.context.stompClient.subscribe(destination, callback);
        // @ts-ignore
        // this.context.stompClient.publish({destination: '/app/getCountProductInCart', body: localStorage.getItem('accessToken')});
    };


}


        // const sock = new SockJS(`${URL}/ws`);
        // this.stompClient = over(sock);
        // this.stompClient.connect({}, () => { this.onConnected(destination, callback); }, this.onError);
        // return this.stompClient;
    // }
//
//     private onConnected(destination: string, callback: (message: Message) => void): void {
//         if (this.stompClient) {
//             this.stompClient.subscribe(destination, callback);
//             this.sendCountProductsInCart('/app/getCountProductInCart');
//         }
//     }
//
//     private sendCountProductsInCart(path: string): void {
//         if (this.stompClient && this.accessToken) {
//             this.stompClient.send(path, {}, this.accessToken);
//         }
//     }
//
//     private onError(error: Frame | string): void {
//         console.error("WebSocket error:", error);
//     }
//
//     public send(destination: string, body: any): void {
//         if (this.stompClient) {
//             this.stompClient.send(destination, {}, body);
//         }
//     }
//
//     public disconnect(): void {
//         if (this.stompClient) {
//             this.stompClient.disconnect(() => {
//                 console.log("WebSocket disconnected");
//             });
//         }
//     }
// }

export default WebSocketService;
