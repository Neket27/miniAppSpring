import {IUser} from "../../model/user/IUser";
import {makeAutoObservable} from "mobx";
import AuthController from "../../controller/AuthController";

export default class AuthService{
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
            const response =await AuthController.login(username,password);
            localStorage.setItem("token",response.accessToken);
            this.setAuth(true);
            this.setUser(response.user);
            let user:IUser={
                id: response.user.id,
                email:response.user.email,
                isActivated:response.user.isActivated,
            };

            this.setUser(user);
        }catch (e){
            // @ts-ignore
            console.log(e.response.message);
        }
    }

    async registration(username:string,password:string){
        try{
            const response =await AuthController.registration(username,password);
            console.log(response);
            localStorage.setItem('token',response.accessToken);
            this.setAuth(true);
            let user:IUser={
                id: response.user.id,
                email:response.user.email,
                isActivated:response.user.isActivated,
            };
            this.setUser(user);
        }catch (e){
            // @ts-ignore
            console.log(e.response.message);
        }
    }

    async logout(){
        try{
            const response =await AuthController.logout();
            console.log(response);
            localStorage.removeItem('token');
            this.setAuth(false);
            this.setUser({} as IUser);
        }catch (e){
            // @ts-ignore
            console.log(e.response?.data?.message);
        }
    }

    async resetPassword(password:string,newPassword:string){
        try {
            const response =await AuthController.resetPassword(password,newPassword);
            localStorage.setItem("token",response.accessToken);
            this.setAuth(true);
            this.setUser(response.user);
            const user:IUser={
                id: response.user.id,
                email:response.user.email,
                isActivated:response.user.isActivated,
            };

            this.setUser(user);
        }catch (e){
            // @ts-ignore
            console.log(e.response?.data?.message);
        }
    }

    //если пользователь авторизован, то у него будет сохранен refresh токен в cooke
    //пока срок годности refresh токена не истек, можно запросить новую пару токенов у сервера
    //иначе: localStorage.setItem('token',response.data.accessToken); установится в null
    //и в другом месте можно будет проверить авторизацию пользователя сделав   if(localStorage.getItem('token')) и в этом условии вызвать checkAuth()
    async checkAuth(){
        this.setLoading(true);
        try {
            // const response = await axios.post<AuthResponse>(`${API_URL}/api/v1/auth/refresh`,{withCredentials:true});
            const response = await AuthController.refresh();
            localStorage.setItem('token',response.accessToken);
            this.setAuth(true);
            const user:IUser={
                id: response.user.id,
                email:response.user.email,
                isActivated:response.user.isActivated,
            };
            this.setUser(user);
        }catch (e){
            console.log("Пользователь не авторизован или срок действия refresh токена истёк, пройдите авторизацию заново");
        }finally {
            this.setLoading(false);
        }
    };

}