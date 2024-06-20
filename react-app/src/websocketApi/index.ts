import { Client, Frame, Message, over } from "stompjs";
import SockJS from "sockjs-client";

const URL = import.meta.env.VITE_URL;

export let stompClient: Client | null;

class WebsocketApi {
    private accessToken: string | null;

    constructor() {
        stompClient = null;
        this.accessToken = localStorage.getItem('accessToken');
    }

    public connect(destination: string, callback: (message: Message) => void) {
        const sock = new SockJS(`${URL}/ws`);
        stompClient = over(sock);
        stompClient.connect({}, () => { this.onConnected(destination, callback); }, this.onError);
        return stompClient
    }

    private onConnected(destination: string, callback: (message: Message) => void): void {
        if (stompClient) {
            stompClient.subscribe(destination, callback);
           // this.sendCountProductsInCart('/app/getCountProductInCart');
        }
    }

    private sendCountProductsInCart(path: string): void {
        if (stompClient && this.accessToken) {
            stompClient.send(path, {}, this.accessToken);
        }
    }

    private onError(error: Frame | string): void {
        console.error("WebSocket error:", error);
    }

    public send(destination: string, body: any): void {
        // if (stompClient) {
        //     stompClient.send(destination, {}, body);
        // }

        if (stompClient?.ws && stompClient.ws.readyState === SockJS.OPEN) {
            stompClient.send('/app/getCountProductInCart', {}, body);
        } else {
            console.error('WebSocket is not open. ReadyState:', stompClient?.ws.readyState);
        }
    }


    public disconnect(): void {
        if (stompClient) {
            stompClient.disconnect(() => {
                console.log("WebSocket disconnected");
            });
        }
    }






}

export default WebsocketApi;
