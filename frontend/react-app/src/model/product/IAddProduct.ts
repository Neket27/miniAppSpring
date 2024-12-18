import {IImage} from "./IImage";

export interface IAddProduct {
    category: string;
    // subcategory: string;
    name: string;
    cost: number;
    rating: number;
    description: string;
    brand: string;
    note: string;
    available: boolean;
    stock: number;
    detail: string;
    producerCountry: string;
    sellerWarranty: number;
    createImageDtoList: IImage[];
}

