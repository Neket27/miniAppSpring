import {PayController} from "../../controller/PayController";

export class PayService {

    async getDataPay(city:string){
        return PayController.getDataPay(city);
    }
}