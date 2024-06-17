import {ICharacteristic} from "../../product/ICharacteristic";
import {ICategory} from "../../product/ICategory";

export interface ProductDetailResponse{
    id:number,
    categoryProduct:ICategory,
    name:string,
    cost:number,
    description:string,
    brand:string,
    article:string
    available:boolean,
    stock:number
    characteristicProduct:ICharacteristic
    detail:string

}