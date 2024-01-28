import api from "../../http";
import {CardProductResponse} from "../model/response/CardProductResponse";
import {ICardProduct} from "../model/ICardProduct";
import {ProductDetailResponse} from "../model/response/ProductDetailResponse";
import {CategoryResponse} from "../model/response/CategoryResponse";


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

}