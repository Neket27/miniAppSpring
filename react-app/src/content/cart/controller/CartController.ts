import ProductService from "../../../product/service/productService";
import {IProductCart} from "../../../product/model/IProductCart";

export default class CartController {
  static  async  getProductsFromCart(accessToken: string) {
        try {
            const response = await ProductService.getProductsFromCart(accessToken);
            return response; // Возвращаем response
        } catch (error) {
            console.error("Error fetching products from cart:", error);
            throw error;
        }
    }

    static  async  getProductFromCart(idProduct:number,accessToken: string) {
        try {
            const response = await ProductService.getProductFromCart(idProduct,accessToken);
            console.log("ProductFromCart");
            console.log(response);

            return response; // Возвращаем response
        } catch (error) {
            console.error("Error fetching products from cart:", error);
            throw error;
        }
    }

 static   async addProductInCart(productCart:IProductCart){
     const response =   await ProductService.addProductInCart(productCart);
     return response;
    }

  static  async  removeProductFromCart(idProduct: number, accessToken: string) {
        const response = await ProductService.removeProductFromCart(idProduct, accessToken);
        return response;
    }

    static  async  increaseProductInCart(idProduct: number, accessToken: string) {
        const response = await ProductService.increaseProductInCart(idProduct, accessToken);
        return response;
    }

    static  async  decreaseProductInCart(idProduct: number,  accessToken: string) {
        const response = await ProductService.decreaseProductInCart(idProduct, accessToken);
        return response;
    }

    static  async  sendCountProductInCart(idProduct: number,count:number, accessToken: string) {
        const response = await ProductService.sendCountProductInCart(idProduct,count, accessToken);
        return response;
    }

}