import api from "../http";
import {CardProductResponse} from "../model/response/product/CardProductResponse";
import {CategoryResponse} from "../model/response/product/CategoryResponse";
import {ICategory} from "../model/product/ICategory";
import {CategorySearchResponse} from "../model/response/product/CategorySearchResponse";
import {FeedbackResponse} from "../model/response/rating/FeedbackResponse";
import {IFeedback} from "../model/rating/IFeedback";
import {IDetailProduct} from "../model/product/IDetailProduct";
import {ICardProduct} from "../model/product/ICardProduct";
import {IProductBag} from "../model/product/IProductBag";
import {IAddProduct} from "../model/product/IAddProduct";

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
        return api.post<CardProductResponse>('/api/v1/category/product',category)
            .then(response=>response.data);
    }

    static async search(searchValue: string) {
        return api.get<Array<CategorySearchResponse>>('/api/v1/search?category='+searchValue)
            .then(response=>response.data);
    }

    static async addProduct(product:IAddProduct){
        return api.post<IProductBag>('/api/v1/home/product/add',product);
    }

    static async updateProduct(product:IAddProduct){
        return api.post<IDetailProduct>('/api/v1/home/product/update',product);
    }

    static async deleteProduct(productId: number) {
        await api.delete(`/api/v1/home/product/delete`, {
            params: { productId } // передаем productId как query-параметр
        });
    }

}