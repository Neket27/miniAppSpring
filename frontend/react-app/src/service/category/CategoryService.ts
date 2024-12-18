import {CategoryController} from "../../controller/CategoryController";

export class CategoryService {

    public async getCategories(){
       return  await CategoryController.getCategories();
    }

    public async getMapCategory(){
        return  await CategoryController.getMapCategory();
    }

    public async addCategory(categoryName:string){
       return  await CategoryController.createCategory(categoryName);
    }
}