import {IImage} from "./IImage";

export interface IProductBag {
    idProduct:number,
    name:string;
    cost:number;
    count:number;
    showInCart:boolean
    imageDtoList:Array<IImage>

}