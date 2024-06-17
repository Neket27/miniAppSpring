import api from "../http";
import {AuthResponse} from "../model/response/auth/AuthResponse";
import {AxiosResponse} from "axios";

export default class AuthController {

    static async login(username:string, password:string):Promise<AuthResponse>{

        return api.post<AuthResponse>('/api/v1/auth/signin',{username,password})
            .then(response=>response.data);

    }

    static async registration(username:string, password:string):Promise<AuthResponse>{
        // @ts-ignore
        return api.post<AuthResponse>('/api/v1/auth/singup',{username,password})
            .then(response=>response.data);
    }

    static async refresh():Promise<AuthResponse>{
        return api.get<AuthResponse>('/api/v1/auth/refresh')
            .then(response=>response.data);
    }


    static async logout(): Promise<void>{
        return api.post('/api/v1/auth/logout');
    }

    static async resetPassword(password:string,newPassword:string):Promise<AuthResponse>{
        return api.post<AuthResponse>('/api/v1/auth/resetPassword',{password,newPassword})
            .then(response=>response.data);

    }
}