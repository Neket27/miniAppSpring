import {IImage} from "../product/IImage";

export interface IUpdateDataUserInCabinet {
    accessToken: string;
    firstname:string;
    lastname:string;
    email:string;
    avatar:IImage|null|undefined
}