import {IUpdateDataUserInCabinet} from "../model/user/IUpdateDataUserInCabinet";
import api from "../http";

export class UserController {

    static async updateDataUserInCabinet(updateDataUserInCabinet:IUpdateDataUserInCabinet): Promise<IUpdateDataUserInCabinet> {
        console.log("данные IN CONTROLLER= "+updateDataUserInCabinet.firstname);
        return api.post<IUpdateDataUserInCabinet>("api/v1/user/updateDataUser",updateDataUserInCabinet)
            .then(response => response.data);
    }


}