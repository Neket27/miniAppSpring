import {ICharacteristic} from "../ICharacteristic";
import {ICategory} from "../ICategory";

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