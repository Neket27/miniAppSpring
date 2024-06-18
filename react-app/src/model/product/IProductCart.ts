import {IImageProduct} from "./IImageProduct";

export interface IProductCart{
    idProduct:number,
    name:string;
    cost:number;
    count:number;
    showInCart:boolean
    imageDtoList:Array<IImageProduct>

}