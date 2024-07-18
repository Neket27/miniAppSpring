import { ICharacteristic} from "./ICharacteristic";
import {ICategory} from "./ICategory";
import {IImage} from "./IImage";

export interface IDetailProduct{
    id:number,
    categoryProduct:ICategory,
    name:string,
    cost:number,
    description:string,
    brand:string,
    article:string
    available:boolean,
    stock:number
    characteristicProductDto:ICharacteristic
    detail:string
    imageDtoList: Array<IImage>
}