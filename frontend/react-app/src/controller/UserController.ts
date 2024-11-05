import {IUpdateDataUserInCabinet} from "../model/user/IUpdateDataUserInCabinet";
import api from "../http";
import {ICreateSupportMessage} from "../model/support/ICreateSupportMessage";
import {IUserDelivery} from "../model/user/IUserDelivery";

export class UserController {

    static async updateDataUserInCabinet(updateDataUserInCabinet:IUpdateDataUserInCabinet): Promise<IUpdateDataUserInCabinet> {
        return api.post<IUpdateDataUserInCabinet>("api/v1/user/updateDataUser",updateDataUserInCabinet)
            .then(response => response.data);
    }

    static async changeDataUserAboutDelivery(userDeliveryUpdateData:IUserDelivery){
        console.log(userDeliveryUpdateData.city);
        return api.post<IUserDelivery>('api/v1/user/updateDataUserAboutDelivery',userDeliveryUpdateData);
    }


    static async getDataUserAboutDelivery():Promise<IUserDelivery> {
        return api.get<IUserDelivery>('api/v1/user/dataUserAboutDelivery').then(r=>r.data);
    }
}