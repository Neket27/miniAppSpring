import {IImage} from "../product/IImage";

export interface ICreateSupportMessage{
    nameUser: string;
    email: string;
    message: string;
    imageDtoList:Array<IImage>
}