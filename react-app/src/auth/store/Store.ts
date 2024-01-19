import {IUser} from "../model/IUser";
// @ts-ignore
import AuthService from "../service/AuthService";
import { makeAutoObservable} from "mobx";
import axios from "axios";
import {AuthResponse} from "../model/response/AuthResponse";
import {API_URL} from "../http";

export default class Store{
    user={} as IUser;
    isAuth = false;
    isLoading=false;

    constructor() {
        makeAutoObservable(this);
    }
    setAuth(bool:boolean){
        this.isAuth=bool;
    }

    setUser(user:IUser){
        this.user=user;
    }

    setLoading(bool:boolean){
        this.isLoading=bool;
    }

    async login(username:string,password:string){
        try{
            const response =await AuthService.login(username,password);
            console.log(response);

            // @ts-ignore
            localStorage.getItem('token',response.data.accessToken);
            this.setAuth(true);
            // this.setUser(response.data.user);
            let user:IUser={
                id:'1',
                email:'nik@gmail.com',
                isActivated:true,
            };

            this.setUser(user);
        }catch (e){
            // @ts-ignore
            console.log(e.response?.data?.message);
        }
    }

    async registration(username:string,password:string){
        try{
            const response =await AuthService.registration(username,password);
            console.log(response);
            // @ts-ignore
            localStorage.getItem('token',response.data.accessToken);
            this.setAuth(true);
            // this.setUser(response.data.user);
            let user:IUser={
                id:'1',
                email:'nik@gmail.com',
                isActivated:true,
            };
            this.setUser(user);
        }catch (e){
            // @ts-ignore
            console.log(e.response?.data?.message);
        }
    }

    async logout(){
        try{
            const response =await AuthService.logout();
            console.log(response);
            // @ts-ignore
            localStorage.removeItem('token');
            this.setAuth(false);
            this.setUser({} as IUser);
        }catch (e){
            // @ts-ignore
            console.log(e.response?.data?.message);
        }
    }

    async checkAuth(){
        this.setLoading(true);
        try {
            const response = await axios.get<AuthResponse>(`${API_URL}/api/v1/auth/refresh`,{withCredentials:true});
            console.log(response);
            localStorage.setItem('token',response.data.accessToken);
            this.setAuth(true);
            this.setUser(response.data.user);
            }catch (e){
            // @ts-ignore
            console.log(e.response?.data?.message);
        }finally {
            this.setLoading(false);
        }
    };

}