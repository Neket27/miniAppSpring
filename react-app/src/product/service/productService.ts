import api from "../../http";
import {CardProductResponse} from "../model/response/CardProductResponse";
import {ProductDetailResponse} from "../model/response/ProductDetailResponse";
import {CategoryResponse} from "../model/response/CategoryResponse";
import {ICategory} from "../model/ICategory";
import {CategorySearchResponse} from "../model/response/CategorySearchResponse";
import {FeedbackResponse} from "../../content/rating/response/FeedbackResponse";
import {IFeedback} from "../../content/rating/model/IFeedback";
import {IProductCart} from "../model/IProductCart";
import {ProductCartResponse} from "../model/response/ProductCartResponse";


export default class ProductService{
    static async getCardsProduct(){
        return  api.get<CardProductResponse>('/api/v1/home/products')
            .then(response=>response.data);
    }

    static async getProductDetail(id:number){
        return api.get<ProductDetailResponse>('/api/v1/productDetail?id='+id)
            .then(response =>response.data);
    }

    static async getMapCategory(){
        return api.get<CategoryResponse>('/api/v1/category')
            .then(response=>response.data);
    }

    static async getProductsByCategory(category:ICategory){
        console.log("categoryInService")
        console.log(category.categoryProduct)
        return api.post<CardProductResponse>('/api/v1/category/product/'+category.categoryProduct,category)
            .then(response=>response.data);
    }

    static async search(searchValue: string) {
        return api.get<Array<CategorySearchResponse>>('/api/v1/search?category='+searchValue)
            .then(response=>response.data);
    }

    static async getProductRatings(idProduct:number){
        return api.get<Array<FeedbackResponse>>('/api/v1/feedback')
            .then(response=>response.data);
    }

    static async addRating(feedback:IFeedback){
        return api.post<IFeedback>('/api/v1/feedback/add',feedback)
            .then(response=>response.data);
    }

    static async addProductInCart(productCart:IProductCart){
        return api.post<Array<ProductCartResponse>>('/api/v1/cart/add',productCart)
            .then(response=>response.data);
    }

    static async getProductFromCart(idProduct:number, accessToken:string){
        return api.get<ProductCartResponse>(`/api/v1/cart/getProduct?idProduct=${idProduct}&accessToken=${accessToken}`)
            .then(response=>response.data);
    }

    static async getProductsFromCart(accessToken:string){
        return api.get<Array<ProductCartResponse>>('/api/v1/cart?accessToken='+accessToken)
            .then(response=>response.data);
    }

    static async increaseProductInCart(idProduct:number,accessToken:string){
        console.log("Токен");
        console.log(accessToken)
        return api.get<boolean>(`/api/v1/cart/increase?idProduct=${idProduct}&accessToken=${accessToken}`)
            .then(response=>response.data);
    }

    static async decreaseProductInCart(idProduct:number, accessToken:string){
        console.log("Токен");
        console.log(accessToken)
        return api.get<boolean>(`/api/v1/cart/decrease?idProduct=${idProduct}&accessToken=${accessToken}`)
            .then(response=>response.data);
    }

    static async sendCountProductInCart(idProduct:number,count:number,accessToken:string){
        console.log("Токен");
        console.log(accessToken)
        return api.get<boolean>(`/api/v1/cart/sendCountProductInCart?idProduct=${idProduct}&count=${count}&accessToken=${accessToken}`)
            .then(response=>response.data);
    }

    static async removeProductFromCart(idProduct:number,accessToken:string){
        console.log("Токен");
        console.log(accessToken)
        return api.get<Array<ProductCartResponse>>(`/api/v1/cart/remove?idProduct=${idProduct}&accessToken=${accessToken}`)
            .then(response=>response.data);
    }

    static async getCountProductInCart( accessToken:string){
        return api.get<number>(`/api/v1/cart/count?accessToken=${accessToken}`)
            .then(response=>response.data);
    }

}