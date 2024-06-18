import { Client, Frame, Message, over } from "stompjs";
import SockJS from "sockjs-client";

const URL = import.meta.env.VITE_URL;

class WebsocketApi {
    private stompClient: Client | null;
    private accessToken: string | null;

    constructor() {
        this.stompClient = null;
        this.accessToken = localStorage.getItem('accessToken');
    }

    public connect(destination: string, callback: (message: Message) => void) {
        const sock = new SockJS(`${URL}/ws`);
        this.stompClient = over(sock);
        this.stompClient.connect({}, () => { this.onConnected(destination, callback); }, this.onError);
    return this.stompClient;
    }

    private onConnected(destination: string, callback: (message: Message) => void): void {
        if (this.stompClient) {
            this.stompClient.subscribe(destination, callback);
            this.sendCountProductsInCart('/app/getCountProductInCart');
        }
    }

    private sendCountProductsInCart(path: string): void {
        if (this.stompClient && this.accessToken) {
            this.stompClient.send(path, {}, this.accessToken);
        }
    }

    private onError(error: Frame | string): void {
        console.error("WebSocket error:", error);
    }

    public send(destination: string, body: any): void {
        if (this.stompClient) {
            this.stompClient.send(destination, {}, body);
        }
    }

    public disconnect(): void {
        if (this.stompClient) {
            this.stompClient.disconnect(() => {
                console.log("WebSocket disconnected");
            });
        }
    }
}

export default WebsocketApi;
