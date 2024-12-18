import api from "../http";
import {ICategory} from "../model/product/ICategory";
import {SearchResponse} from "../model/response/product/SearchResponse";
import {IDetailProduct} from "../model/product/IDetailProduct";
import {ICardProduct} from "../model/product/ICardProduct";
import {IProductBag} from "../model/product/IProductBag";
import {IAddProduct} from "../model/product/IAddProduct";

export default class ProductController {

    static async getCardsProduct(): Promise<Array<ICardProduct>> {
        return api.get<ICardProduct[]>('/api/v1/products')
            .then(response => response.data);
    }

    static async getDiscountedProducts() {
        return api.get<ICardProduct[]>('/api/v1/discounts')
            .then(response => response.data);
    }


    static async getProductDetail(id: number): Promise<IDetailProduct> {
        return api.get<IDetailProduct>('/api/v1/products/detail?id=' + id)
            .then(response => response.data);
    }

    static async getProductsByCategory(category: ICategory): Promise<Array<ICardProduct>> {
        return api.post<Array<ICardProduct>>('/api/v1/category/products', category)
            .then(response => response.data);
    }

    static async search(searchText: string) {
        return api.get<Array<SearchResponse>>('/api/v1/search?searchText=' + searchText)
            .then(response => response.data);
    }

    static async addProduct(product: IAddProduct) {
        return api.post<IProductBag>('/api/v1/products/add', product);
    }

    static async updateProduct(product: IAddProduct) {
        return api.post<IDetailProduct>('/api/v1/products/update', product);
    }

    static async deleteProduct(productId: number) {
        await api.delete(`/api/v1/products/delete`, {
            params: {productId} // передаем productId как query-параметр
        });
    }

}