import api from "../http";
import {ICoupon} from "../model/coupon/ICoupon";
import {ICreateCoupon} from "../model/coupon/ICreateCoupon";

export default class CouponController {

    static async checkCoupon(coupon: string) {
        return api.get<ICoupon>(`/api/v1/coupons/check?coupon=${coupon}`).then(response=>response.data);
    }

    static async createCoupon(coupon:ICreateCoupon) {
        return api.post<ICreateCoupon>('/api/v1/coupons/add',coupon).then(response=>response.data);
    }

}