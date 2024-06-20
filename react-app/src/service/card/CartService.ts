import {ProductCartResponse} from "../../model/response/product/ProductCartResponse";
import {IProductCart} from "../../model/product/IProductCart";
import CartController from "../../controller/CartController";
import WebsocketApi from "../../websocketApi";
import WebSocketService from "../ws/WebSocketService";
import {Client, Message} from '@stomp/stompjs';
import {Context, State} from "../../main";
import {useContext} from "react";


export default class CartService {


    static  async  getProductsFromCart(accessToken: string):Promise<ProductCartResponse> {
        try {
            const response = await CartController.getProductsFromCart(accessToken);
            return {
                productsInCard: response
            };
        } catch (error) {
            console.error("Error fetching products from cart:", error);
            throw error;
        }
    }

    static  async  getProductFromCart(idProduct:number,accessToken: string):Promise<ProductCartResponse> {
        try {
            return await CartController.getProductFromCart(idProduct, accessToken); // Возвращаем response
        } catch (error) {
            console.error("Error fetching products from cart:", error);
            throw error;
        }
    }

    static   async addProductInCart(productCart:IProductCart){
        return await CartController.addProductInCart(productCart);
    }

    static  async  removeProductFromCart(idProduct: number, accessToken: string):Promise<ProductCartResponse> {
        const response = await CartController.removeProductFromCart(idProduct, accessToken);
        return {
            productsInCard: response
        };
    }

    static  async  increaseProductInCart(idProduct: number, accessToken: string) {
        return await CartController.increaseProductInCart(idProduct, accessToken);
    }

    static  async  decreaseProductInCart(idProduct: number,  accessToken: string) {
        return await CartController.decreaseProductInCart(idProduct, accessToken);
    }

    static  async  sendCountProductInCart(idProduct: number,count:number, accessToken: string) {
        return await CartController.sendCountProductInCart(idProduct, count, accessToken);
    }

   static getCountProductInCart(callback: (message: Message) => void){
        const webSocketService = new WebSocketService();
       webSocketService.connect('/shoppingCartCountProduct/public', callback);
       // this.sendCountProductCart(websocketApi, localStorage.getItem('accessToken'));
    }

    static sendCountProductCart(context:State): void {
        console.log("sendCountProductCart");
        context.stompClient.activate();
        console.log("client con2= "+context.stompClient.connected);
        if(context.stompClient.connected && context.stompClient.active){} {
            // @ts-ignore
            context.stompClient.publish({destination: '/app/getCountProductInCart', body: localStorage.getItem('accessToken')});
            console.log("SEND")
        }
        }

}