import { ICharacteristic} from "./ICharacteristic";
import {ICategory} from "./ICategory";
import {IImage} from "./IImage";

export interface IDetailProduct{
    id:number,
    category:string,
    name:string,
    cost:number,
    description:string,
    brand:string,
    note:string
    available:boolean,
    stock:number
    characteristicProductDto:ICharacteristic
    detail:string
    imageDtoList: Array<IImage>
}