import {ICategory} from "./ICategory";
import {IImage} from "./IImage";

export interface ICardProduct{
    id:number,
    categoryProduct:ICategory,
    name:string,
    cost:number,
    rating:number,
    discount:number,
    imageDtoList:Array<IImage>
}