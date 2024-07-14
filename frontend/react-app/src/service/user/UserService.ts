import {AxiosResponse} from "axios";
import {IUser} from "../../model/user/IUser";
import api from "../../http";
import {UserController} from "../../controller/UserController";
import {IUpdateDataUserInCabinet} from "../../model/user/IUpdateDataUserInCabinet";

export default class UserService{

    static fetchUsers():Promise<AxiosResponse<IUser[]>>{
        return api.get<IUser[]>('/users');
    }

    static async updateDataUser(updateDataUserInCabinet: IUpdateDataUserInCabinet): Promise<IUpdateDataUserInCabinet> {
        console.log("data in service= "+updateDataUserInCabinet.firstname);
        return await UserController.updateDataUserInCabinet(updateDataUserInCabinet);
    }

}