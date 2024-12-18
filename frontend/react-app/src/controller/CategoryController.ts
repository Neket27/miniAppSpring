import api from "../http";
import {CategoryResponse} from "../model/response/product/CategoryResponse";

export class CategoryController {

    public static async getCategories(){
        return await api.get<Array<string>>("/api/v1/category/categories").then(r => r.data);
    }

    static async getMapCategory(){
        return api.get<CategoryResponse>('/api/v1/category')
            .then(response=>response.data);
    }


    public static async createCategory(categoryName:string):Promise<string>{
        return api.post<string>('/api/v1/category/add?categoryName='+categoryName).then(r=>r.data);
    }
}