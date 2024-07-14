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

    constructor() {
        makeAutoObservable(this);
    }
    setAuth(bool:boolean){
        this.isAuth=bool;
    }

    setUser(user:IUser){
        this.user=user;
    }

    async login(username:string,password:string){
        try{
            const response =await AuthService.login(username,password);
           console.log(response);
            // @ts-ignore
            localStorage.getItem('token',response.data.accessToken);
            this.setAuth(true);
            this.setUser(response.data.user);
        }catch (e){
            // @ts-ignore
            console.log(e.response?.data?.message);
        }
    }

    async registration(username:string,password:string){
        try{
            const response =await AuthService.login(username,password);
            console.log(response);
            // @ts-ignore
            localStorage.getItem('token',response.data.accessToken);
            this.setAuth(true);
            this.setUser(response.data.user);
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
        try {
            const response = await axios.get<AuthResponse>(`${API_URL}/refresh`,{withCredentials:true});
            console.log(response);
            localStorage.setItem('token',response.data.accessToken);
            this.setAuth(true);
            this.setUser(response.data.user);
            }catch (e){
            // @ts-ignore
            console.log(e.response?.data?.message);
        }
    };

}