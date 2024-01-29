import api from "../../http";
import {CardProductResponse} from "../model/response/CardProductResponse";
import {ProductDetailResponse} from "../model/response/ProductDetailResponse";
import {CategoryResponse} from "../model/response/CategoryResponse";
import {ICategory} from "../model/ICategory";
import {CategorySearchResponse} from "../model/response/CategorySearchResponse";


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

    static async getProductByCategory(category:ICategory){

        return api.post<CardProductResponse>('/api/v1/category/product/'+category.categoryProduct,category)
            .then(response=>response.data);
    }

    static search(searchValue: string) {
        return api.get<Array<CategorySearchResponse>>('/api/v1/search?category='+searchValue)
            .then(response=>response.data);
    }
}