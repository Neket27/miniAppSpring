import {IImageProduct} from "../../product/IImageProduct";

export interface ProductCartResponse{
    idProduct:number,
    name:string;
    cost:number;
    count:number;
    showInCart:boolean
    imageDtoList:Array<IImageProduct>
}