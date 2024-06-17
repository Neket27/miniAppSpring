import {ICharacteristic} from "./ICharacteristic";
import {ICategory} from "./ICategory";
import {IImageProduct} from "./IImageProduct";

export interface ICardProduct{
    id:number,
    categoryProduct:ICategory,
    name:string,
    cost:number,
    rating:number,
    imageDtoList:Array<IImageProduct>
}