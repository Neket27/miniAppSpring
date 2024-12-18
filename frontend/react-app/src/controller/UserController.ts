import {IUpdateDataUserInCabinet} from "../model/user/IUpdateDataUserInCabinet";
import api from "../http";
import {IUserDelivery} from "../model/user/IUserDelivery";

export class UserController {

    static async updateDataUserInCabinet(updateDataUserInCabinet:IUpdateDataUserInCabinet): Promise<IUpdateDataUserInCabinet> {
        return api.post<IUpdateDataUserInCabinet>("api/v1/user/data/update",updateDataUserInCabinet)
            .then(response => response.data);
    }

    static async changeDataUserAboutDelivery(userDeliveryUpdateData:IUserDelivery){
        return api.post<IUserDelivery>('api/v1/user/data/update/delivery',userDeliveryUpdateData);
    }


    static async getDataUserAboutDelivery():Promise<IUserDelivery> {
        return api.get<IUserDelivery>('api/v1/user/data/delivery').then(r=>r.data);
    }
}