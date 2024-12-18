import {UserController} from "../../controller/UserController";
import {IUpdateDataUserInCabinet} from "../../model/user/IUpdateDataUserInCabinet";
import {IUserDelivery} from "../../model/user/IUserDelivery";

export default class UserService{

    static async updateDataUser(updateDataUserInCabinet: IUpdateDataUserInCabinet): Promise<IUpdateDataUserInCabinet> {
        return await UserController.updateDataUserInCabinet(updateDataUserInCabinet);
    }

    static async changeDataUserAboutDelivery(userDeliveryUpdateData:IUserDelivery){
        return await UserController.changeDataUserAboutDelivery(userDeliveryUpdateData);
    }

    static async getDataUserAboutDelivery() {
        return await UserController.getDataUserAboutDelivery();
    }
}