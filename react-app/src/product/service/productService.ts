import {AxiosResponse} from "axios";
import api from "../../http";
import {CardProductResponse} from "../model/response/CardProductResponse";
import {ICardProduct} from "../model/ICardProduct";


export default class ProductService{
    static async getCardsProduct(){
        return  api.get<CardProductResponse>('/api/v1/home/products')
            .then(response=>response.data);
    }
}