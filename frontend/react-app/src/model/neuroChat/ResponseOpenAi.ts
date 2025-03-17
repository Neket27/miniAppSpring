import {ICardProduct} from "../product/ICardProduct";

export interface ResponseOpenAi extends Response {
    message: string,
    productCards:Array<ICardProduct>
}