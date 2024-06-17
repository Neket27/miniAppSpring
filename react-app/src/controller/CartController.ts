import ProductControllerP from "./productControllerP";
import {IProductCart} from "../model/product/IProductCart";
import {ProductCartResponse} from "../model/response/product/ProductCartResponse";

export default class CartController {
  static  async  getProductsFromCart(accessToken: string) {
        try {
            const response = await ProductControllerP.getProductsFromCart(accessToken);
            return response; // Возвращаем response
        } catch (error) {
            console.error("Error fetching products from cart:", error);
            throw error;
        }
    }

    static  async  getProductFromCart(idProduct:number,accessToken: string):Promise<ProductCartResponse> {
        try {
            return await ProductControllerP.getProductFromCart(idProduct, accessToken); // Возвращаем response
        } catch (error) {
            console.error("Error fetching products from cart:", error);
            throw error;
        }
    }

 static   async addProductInCart(productCart:IProductCart){
     const response =   await ProductControllerP.addProductInCart(productCart);
     return response;
    }

  static  async  removeProductFromCart(idProduct: number, accessToken: string) {
        const response = await ProductControllerP.removeProductFromCart(idProduct, accessToken);
        return response;
    }

    static  async  increaseProductInCart(idProduct: number, accessToken: string) {
        const response = await ProductControllerP.increaseProductInCart(idProduct, accessToken);
        return response;
    }

    static  async  decreaseProductInCart(idProduct: number,  accessToken: string) {
        const response = await ProductControllerP.decreaseProductInCart(idProduct, accessToken);
        return response;
    }

    static  async  sendCountProductInCart(idProduct: number,count:number, accessToken: string) {
        const response = await ProductControllerP.sendCountProductInCart(idProduct,count, accessToken);
        return response;
    }

    // static async getCountProductInCart(accessToken: string){
    //     const response = await ProductControllerP.getCountProductInCart(accessToken);
    //     return response;
    // }

}