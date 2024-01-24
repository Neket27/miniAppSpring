import api from '../../http';
import {AxiosResponse} from "axios";
import {AuthResponse} from "../model/response/AuthResponse";

export default class AuthService{
    static async login(username:string, password:string):Promise<AxiosResponse>{

        return api.post<AuthResponse>('/api/v1/auth/signin',{username,password})
            .then(response=>response);

    }

    static async registration(username:string, password:string):Promise<AxiosResponse>{
        // @ts-ignore
        return api.post<AuthResponse>('/api/v1/auth/singup',{username,password})
            .then(response=>response);
    }

    static async refresh():Promise<AxiosResponse>{
        return api.get<AuthResponse>('/api/v1/auth/refresh')
            .then(response=>response);
    }


    static async logout(): Promise<void>{
        return api.post('/api/v1/auth/logout');
    }

    static async resetPassword(password:string,newPassword:string):Promise<AxiosResponse>{

        return api.post<AuthResponse>('/api/v1/auth/resetPassword',{password,newPassword})
            .then(response=>response);

    }
}