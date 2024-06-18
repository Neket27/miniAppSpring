import {ProductCartResponse} from "../../model/response/product/ProductCartResponse";
import {IProductCart} from "../../model/product/IProductCart";
import CartController from "../../controller/CartController";

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


}