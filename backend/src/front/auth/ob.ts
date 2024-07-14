// import {makeAutoObservable} from "mobx";
// import axios, {AxiosResponse} from "axios";
//
//
// export interface IUser{
//     email:string;
//     isActivated: boolean;
//     id:string;
// }
//
// export interface AuthResponse{
//     accessToken: string;
//     refreshToken: string;
//     user:IUser;
// }
//
// export  class UserService{
//     static fetchUsers():Promise<AxiosResponse<IUser[]>>{
//         return api.get<IUser[]>('/users');
//     }
// }
//
// export class AuthService{
//     static async login(username:string, password:string):Promise<AxiosResponse<AuthResponse>>{
//         // @ts-ignore
//         return api.post<AuthResponse>('/api/v1/auth/signin',{username,password})
//             .then(response=>response.data);
//     }
//
//     static async registration(username:string, password:string):Promise<AxiosResponse<AuthResponse>>{
//         // @ts-ignore
//         return api.post<AuthResponse>('/api/v1/auth/singup',{username,password})
//             .then(response=>response.data);
//     }
//
//     static async logout(): Promise<void>{
//         return api.post('/api/v1/auth/logout');
//     }
// }
//
// export  class Store {
//     user = {} as IUser;
//     isAuth = false;
//
//     constructor() {
//         makeAutoObservable(this);
//     }
//
//     setAuth(bool: boolean) {
//         this.isAuth = bool;
//     }
//
//     setUser(user: IUser) {
//         this.user = user;
//     }
//
//     async login(username: string, password: string) {
//         try {
//             const response = await AuthService.login(username, password);
//             console.log(response);
//             // @ts-ignore
//             localStorage.getItem('token', response.data.accessToken);
//             this.setAuth(true);
//             this.setUser(response.data.user);
//         } catch (e) {
//             // @ts-ignore
//             console.log(e.response?.data?.message);
//         }
//     }
//
//     async registration(username: string, password: string) {
//         try {
//             const response = await AuthService.login(username, password);
//             console.log(response);
//             // @ts-ignore
//             localStorage.getItem('token', response.data.accessToken);
//             this.setAuth(true);
//             this.setUser(response.data.user);
//         } catch (e) {
//             // @ts-ignore
//             console.log(e.response?.data?.message);
//         }
//     }
//
//     async logout() {
//         try {
//             const response = await AuthService.logout();
//             console.log(response);
//             // @ts-ignore
//             localStorage.removeItem('token');
//             this.setAuth(false);
//             this.setUser({} as IUser);
//         } catch (e) {
//             // @ts-ignore
//             console.log(e.response?.data?.message);
//         }
//     }
//
//     async checkAuth() {
//         try {
//             const response = await axios.get<AuthResponse>(`${API_URL}/refresh`, {withCredentials: true});
//             console.log(response);
//             localStorage.setItem('token', response.data.accessToken);
//             this.setAuth(true);
//             this.setUser(response.data.user);
//         } catch (e) {
//             // @ts-ignore
//             console.log(e.response?.data?.message);
//         }
//     };
//
// }
//
// // @ts-ignore
// export const API_URL ='http://localhost:8080';
//
// const api = axios.create({
//     withCredentials:true,
//     baseURL: API_URL
// });
//
// api.interceptors.request.use((config:any)=>{
//     config.headers.Authorization=`Bearer ${localStorage.getItem('token')}`;
//     return config;
// });
//
