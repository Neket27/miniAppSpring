import CouponController from "../../controller/CouponController";
import {ICreateCoupon} from "../../model/coupon/ICreateCoupon";

class CouponService {

    public async checkCoupon(coupon: string) {
        return await CouponController.checkCoupon(coupon);
    }

    public async createCoupon(coupon:ICreateCoupon) {
        return await CouponController.createCoupon(coupon);
    }
}
export default CouponService;