import ProductController from "./ProductController";
import {IProductBag} from "../model/product/IProductBag";
import {ProductCartResponse} from "../model/response/product/ProductCartResponse";
import api from "../http";
import {string} from "prop-types";
import {Client, Frame, Message, over} from "stompjs";
import SockJS from "sockjs-client";
import {IProductInBag} from "../model/bag/IProductInBag";

// let stompClient: Client | null;
// let accessToken = localStorage.getItem('accessToken');

export default class BagController {

    static async addProductInCart(productCart:IProductInBag){
        return api.post<Array<ProductCartResponse>>('/api/v1/cart/add',productCart)
            .then(response=>response.data);
    }

    static async getProductFromCart(idProduct:number, accessToken:string){
        return api.get<ProductCartResponse>(`/api/v1/cart/getProduct?idProduct=${idProduct}&accessToken=${accessToken}`)
            .then(response=>response.data);
    }

    static async getProductsFromCart(accessToken:string):Promise<Array<IProductBag>>{
        return api.get<Array<IProductBag>>('/api/v1/cart?accessToken=' + accessToken)
            .then(response => response.data);
    }

    static async increaseProductInCart(idProduct:number,accessToken:string){
        return api.get<boolean>(`/api/v1/cart/increase?idProduct=${idProduct}&accessToken=${accessToken}`)
            .then(response=>response.data);
    }

    static async decreaseProductInCart(idProduct:number, accessToken:string){
        return api.get<boolean>(`/api/v1/cart/decrease?idProduct=${idProduct}&accessToken=${accessToken}`)
            .then(response=>response.data);
    }

    static async sendCountProductInCart(idProduct:number,count:number,accessToken:string){
        return api.get<boolean>(`/api/v1/cart/sendCountProductInCart?idProduct=${idProduct}&count=${count}&accessToken=${accessToken}`)
            .then(response=>response.data);
    }

    static async getCountProductInBag(accessToken:string):Promise<number>{
        return api.get<number>(`/api/v1/cart/getCountProductInBag?accessToken=${accessToken}`)
            .then(response=>response.data);
    }

    static async removeProductFromCart(idProduct:number,accessToken:string):Promise<Array<IProductBag>>{
        return api.get<Array<IProductBag>>(`/api/v1/cart/remove?idProduct=${idProduct}&accessToken=${accessToken}`)
            .then(response=>response.data);
    }

    // static connect(destination: string, callback: (message: Message) => void): void {
    //     const sock = new SockJS(`${URL}/ws`);
    //
    //     stompClient = over(sock);
    //     stompClient.connect({}, () => { this.onConnected(destination, callback); }, this.onError);
    // }
    //
    // static onConnected(destination: string, callback: (message: Message) => void): void {
    //     if (stompClient) {
    //         stompClient.subscribe(destination, callback);
    //         this.sendCountProductsInCart('/app/getCountProductInCart');
    //     }
    // }
    //
    // static sendCountProductsInCart(path: string): void {
    //     if (stompClient && accessToken) {
    //         stompClient.send(path, {}, accessToken);
    //     }
    // }
    //
    // static onError(error: Frame | string): void {
    //     console.error("WebSocket error:", error);
    // }


}