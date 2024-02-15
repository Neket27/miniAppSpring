// import io from 'socket.io-client';
//
// const socket = io(); // Подключение к текущему хосту (или к вашему серверу WebSocket)
//
// export const websocketApi = {
//     post: (path: string, data: any) => {
//         return new Promise((resolve, reject) => {
//             socket.emit('post', { path, data });
//
//             socket.on('response', (responseData: any) => {
//                 resolve(responseData);
//             });
//
//             socket.on('error', (error: any) => {
//                 reject(error);
//             });
//         });
//     }
// };
//
//
//
// export const getCountProductInCart = async (accessToken: string) => {
//     return new Promise<number>((resolve, reject) => {
//         // Отправляем запрос на сервер WebSocket с указанием пути и токена доступа
//         socket.emit('getCountProductInCart', `/api/v1/cart/count?accessToken=${accessToken}`);
//
//         // Обработка ответа от сервера WebSocket
//         socket.on('countProductInCart', (count: number) => {
//             resolve(count);
//         });
//
//         // Обработка ошибок
//         socket.on('error', (error: any) => {
//             reject(error);
//         });
//     });
// };