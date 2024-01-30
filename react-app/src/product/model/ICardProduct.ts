import {ICharacteristic} from "./ICharacteristic";
import {ICategory} from "./ICategory";

export interface ICardProduct{
    id:number,
    categoryProduct:ICategory,
    name:string,
    cost:number,
    rating:number,
}