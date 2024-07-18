// import {io, Socket} from "socket.io-frontend";
//
// export class SocketAPI {
//     static socket:Socket|null = null;
//
//     static createConnection(){
//         this.socket = io("http://localhost:5173/ws/shoppingCartCountProduct/public");
//
//         this.socket.on("connect",()=>{
//             console.log("Connected");
//         });
//
//         this.socket.on("disconnect",(e)=>{
//             console.log("Disconnected");
//         });
//     }
//
// }