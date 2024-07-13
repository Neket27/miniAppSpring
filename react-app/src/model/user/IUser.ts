import {IImage} from "../product/IImage";

export interface IUser{
    username:string;
    firstname:string;
    lastname:string;
    email:string;
    avatar:IImage;
    isActivated: boolean;
    id:string;
}