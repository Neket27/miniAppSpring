import {IImage} from "./IImage";

export interface IUpdateProduct {
    id:number,
    category: string;
    subcategory: string;
    name: string;
    cost: number;
    rating: number;
    description: string;
    brand: string;
    article: string;
    available: boolean;
    stock: number;
    detail: string;
    producerCountry: string;
    sellerWarranty: number;
    updateImageDtoList: IImage[];
}