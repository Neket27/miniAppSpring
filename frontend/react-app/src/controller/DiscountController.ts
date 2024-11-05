import api from "../http";
import {IDiscountCreate} from "../model/discount/IDiscountCreate";
import {IDiscount} from "../model/discount/IDiscount";

export class DiscountController {
    static async checkDiscountAtProduct(productId: string) {
        return api.get<IDiscount>(`/api/v1/discount/check?productId=${productId}`).then(response=>response.data);
    }

    static async createDiscount(discount:IDiscountCreate) {
        console.log("d="+discount.productIdList)
       return api.post<IDiscount>('/api/v1/discount/create',discount).then(response=>response.data);
    }
}