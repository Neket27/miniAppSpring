import api from '../http';
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
            .then(response=>response.data);
    }

    static async logout(): Promise<void>{
        return api.post('/api/v1/auth/logout');
    }
}