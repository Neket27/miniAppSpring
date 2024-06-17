import {AxiosResponse} from "axios";
import {IUser} from "../../model/user/IUser";
import api from "../../http";

export default class UserService{
    static fetchUsers():Promise<AxiosResponse<IUser[]>>{
        return api.get<IUser[]>('/users');
    }
}