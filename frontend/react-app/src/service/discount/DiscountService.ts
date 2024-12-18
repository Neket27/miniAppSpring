import {ICreateCoupon} from "../../model/coupon/ICreateCoupon";
import {DiscountController} from "../../controller/DiscountController";

export class DiscountService {
    public async checkDiscountAtProduct(productId: string) {
        return await DiscountController.checkDiscountAtProduct(productId);
    }

    public async createDiscount(coupon:ICreateCoupon) {
        return await DiscountController.createDiscount(coupon);
    }
}