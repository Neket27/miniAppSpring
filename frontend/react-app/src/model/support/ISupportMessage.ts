import {IImage} from "../product/IImage";

export interface ISupportMessage {
    nameUser:string;
    email: string;
    message: string;
    imageDtoList:Array<IImage>
}