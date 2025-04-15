import api from "../http";
import {IDiscountCreate} from "../model/discount/IDiscountCreate";
import {IDiscount} from "../model/discount/IDiscount";
import {ICardProduct} from "../model/product/ICardProduct";

export class DiscountController {
    static async checkDiscountAtProduct(productId: string) {
        return api.get<IDiscount>(`/api/v1/discounts/check?productId=${productId}`).then(response=>response.data);
    }

    static async createDiscount(discount:IDiscountCreate) {
       return api.post<IDiscount>('/api/v1/discounts/add',discount).then(response=>response.data);
    }

    static getProductWithDiscountByTown(town: string) {
        return api.get<ICardProduct[]>(`/api/v1/discounts/products?town=${town}`, {}).then(response => response.data);
    }
}