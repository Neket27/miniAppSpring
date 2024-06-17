import {ICategory} from "../../product/ICategory";

export interface CategoryResponse{

    numberOfProductsInThisCategory:Map<string,number>;
}