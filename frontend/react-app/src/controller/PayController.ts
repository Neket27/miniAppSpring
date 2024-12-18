import api from "../http";
import {IPayData} from "../model/pay/IPayData";

export class PayController {

    static async getDataPay(city:string):Promise<IPayData>{
        return await api.get<IPayData>('/api/v1/pay/data?city='+city).then(r=>r.data);
    }
}