import api from "../http";
import {AuthResponse} from "../model/response/auth/AuthResponse";

export default class AuthController {

    static async login(username:string, password:string):Promise<AuthResponse>{
        return  api.post<AuthResponse>('/api/v1/auth/signin', {username, password})
            .then(response => {
                const authResponse: AuthResponse = {
                    accessToken: response.data.accessToken,
                    refreshToken: response.data.refreshToken,
                    user: response.data.user,
                    status: response.status
                }
                return authResponse;
            });
    }

    static async registration(username:string, password:string):Promise<AuthResponse>{
        return api.post<AuthResponse>('/api/v1/auth/singup',{username,password})
            .then(response => {
                const authResponse: AuthResponse = {
                    accessToken: response.data.accessToken,
                    refreshToken: response.data.refreshToken,
                    user: response.data.user,
                    status: response.status
                }
                return authResponse;
            });
    }

    static async refresh():Promise<AuthResponse|null>{
        return api.get<AuthResponse|null>('/api/v1/auth/refresh')
            .then(response => {
                if(response.data!=null){
                    const authResponse: AuthResponse = {
                        accessToken: response.data.accessToken,
                        refreshToken: response.data.refreshToken,
                        user: response.data.user,
                        status: response.status
                    }
                    return authResponse;
                }else
                    return null;



            });
    }


    static async logout(): Promise<void>{
        return api.post('/api/v1/auth/logout');
    }

    static async resetPassword(password:string,newPassword:string):Promise<AuthResponse>{
        return api.post<AuthResponse>('/api/v1/auth/resetPassword',{password,newPassword})
            .then(response => {
                const authResponse: AuthResponse = {
                    accessToken: response.data.accessToken,
                    refreshToken: response.data.refreshToken,
                    user: response.data.user,
                    status: response.status
                }
                return authResponse;
            });

    }
}