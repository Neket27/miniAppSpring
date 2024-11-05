import {IImage} from "./IImage";

export interface IAddProduct {
    category: string; // e.g., "CLOTHES"
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
    createImageDtoList: IImage[];
}

