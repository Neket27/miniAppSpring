import {ICardProduct} from "../../model/product/ICardProduct";
import ProductController from "../../controller/ProductController";
import {CardProductResponse} from "../../model/response/product/CardProductResponse";
import {IDetailProduct} from "../../model/product/IDetailProduct";
import CartController from "../../controller/CartController";
import {ProductCartResponse} from "../../model/response/product/ProductCartResponse";
import {IFeedback} from "../../model/rating/IFeedback";

export default class ProductService {

   // @ts-ignore
    static async getListProductOnHomePage(): Promise<ICardProduct[]> {
        try{
            const response:Promise<Array<ICardProduct>> = ProductController.getCardsProduct();
            if(response!=undefined)
                return response;

            return new Array<ICardProduct>;
        }catch (e){
            console.log("Ошибка получения данных в getListProductOnHomePage() = "+e);
        }
    }


    // @ts-ignore
    static async getProductByCategory(category: string):Promise<CardProductResponse> {
        try {
            const response:CardProductResponse = await ProductController.getProductsByCategory({ categoryProduct: category, subcategory: 'unsupported', stringValueCategory: 'mmm' });
            return response
        } catch (error) {
            console.error("Error fetching related products:", error);
        }
    }

    // @ts-ignore
    static async getProductDetail(idProduct:number): Promise<IDetailProduct> {
        try {
            if(idProduct!=undefined)
                return await ProductController.getProductDetail(idProduct);
            else
                console.error("TypeId for 'getProductDetail'= ", idProduct);

        } catch (error) {
            console.error("Error fetching product detail:", error);
        }
    }

    // @ts-ignore
    static async getProductFromCart(idProduct: number, accessToken: string):Promise<ProductCartResponse> {
        try {
            return await CartController.getProductFromCart(idProduct, accessToken);

        } catch (error) {
            console.error("Error fetching product from cart:", error);
        }
    }

    static async sendCountProductInCartUser (idProduct: number, count: number, accessToken: string):Promise<void >  {
        if (!isNaN(count)) {  // Проверка на то, что count является числом
            const response = await CartController.sendCountProductInCart(idProduct, count, accessToken);
        } else {
            console.error("Ошибка: Невалидное значение countProductsInBag");
        }
    };
    static async addRating(feedback:IFeedback):Promise<void>{
        await ProductController.addRating(feedback);
    };
}