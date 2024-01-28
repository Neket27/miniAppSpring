import {ICharacteristic} from "../ICharacteristic";

export interface ProductDetailResponse{
    id:number,
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