import api from "../http";
import {CardProductResponse} from "../model/response/product/CardProductResponse";
import {CategoryResponse} from "../model/response/product/CategoryResponse";
import {ICategory} from "../model/product/ICategory";
import {CategorySearchResponse} from "../model/response/product/CategorySearchResponse";
import {FeedbackResponse} from "../model/response/rating/FeedbackResponse";
import {IFeedback} from "../model/rating/IFeedback";
import {IDetailProduct} from "../model/product/IDetailProduct";
import {ICardProduct} from "../model/product/ICardProduct";

export default class ProductController {

    static async getCardsProduct():Promise<Array<ICardProduct>>{
        return api.get<ICardProduct[]>('/api/v1/home/products')
            .then(response=>response.data);
    }

    static async getProductDetail(id:number):Promise<IDetailProduct>{
        return api.get<IDetailProduct>('/api/v1/productDetail?id='+id)
            .then(response =>response.data);
    }

    static async getMapCategory(){
        return api.get<CategoryResponse>('/api/v1/category')
            .then(response=>response.data);
    }

    static async getProductsByCategory(category:ICategory):Promise<CardProductResponse>{
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


}