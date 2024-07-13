// import { Client, Message } from '@stomp/stompjs';
// import SockJS from "sockjs-client";
// import {useContext} from "react";
// import {Context, State} from "../../main";
// const URL = import.meta.env.VITE_URL;
//
// class WebSocketService {
//     private _accessToken: string | null;
//     private _stompClient:Client;
//
//     get accessToken(): string | null {
//         return this._accessToken;
//     }
//
//     set accessToken(value: string | null) {
//         this._accessToken = value;
//     }
//
//     get stompClient(): Client {
//         return this._stompClient;
//     }
//
//     set stompClient(value: Client) {
//         this._stompClient = value;
//     }
//
//     constructor(stompClient:Client) {
//         this._accessToken = localStorage.getItem("accessToken");
//         this._stompClient = stompClient;
//     }
//
//     public subscribe(destination: string,callback: ((message: Message) => void),path:string,body:any) {
//         this._stompClient.webSocketFactory = () => new SockJS(`${URL}/ws`);
//         this._stompClient.onConnect = () => this.onConnected(destination, callback,path,body);
//         this._stompClient.activate();
//
//         console.log("client con1= "+this._stompClient.connected);
//     }
//
//     private onConnected =(destination: string,callback: (message: Message) => void,path:string,body:any) => {
//         this._stompClient.subscribe(destination, callback);
//         if(body!=null)
//             this.send(path,body);
//         else
//             console.log("Body в onConnected = "+body)
//     };
//
//     public send(destination: string, body:any){
//         if(!this.stompClient.connected)
//             console.log("StompClient не подключен, статус = " + this.stompClient.connected);
//         else
//             this._stompClient.publish({destination: destination, body: body});
//     }
//
// }
//
// export default WebSocketService;
