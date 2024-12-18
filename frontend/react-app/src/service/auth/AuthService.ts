import {IUser} from "../../model/user/IUser";
import {makeAutoObservable} from "mobx";
import AuthController from "../../controller/AuthController";
import {AuthResponse} from "../../model/response/auth/AuthResponse";
import {Roles} from "../../model/role/Roles";

export default class AuthService{
    user:IUser={} as IUser;
    isAuth:boolean = false;
    isLoading:boolean=false;

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


    async login(username:string,password:string) {
        try {
            const response: AuthResponse = await AuthController.login(username, password);
            if (response.status == 200 && typeof response.accessToken === "string" && response.user) {
                localStorage.setItem("accessToken", response.accessToken);
                this.setAuth(true);
                this.setUser(response.user);

            }
        } catch (e:any) {
            if (e.response.status == 403) {
                return 403;

            }
            console.log(e.response.status);

        }
    }

    async registration(username:string,password:string){
        try{
            const response =await AuthController.registration(username,password);
            console.log(response);
            if (typeof response.accessToken === "string" && response.user) {
                localStorage.setItem('accessToken', response.accessToken);
                this.setAuth(true);
                this.setUser(response.user);
            }
        }catch (e){
            // @ts-ignore
            console.log(e.response.message);
        }
    }

    async logout():Promise<boolean|undefined>{
        try{
            const response =await AuthController.logout();
            console.log(response);
            localStorage.removeItem('accessToken');
            localStorage.setItem('countProductInBag', String(0));

            this.setAuth(false);
            this.setUser({} as IUser);
            return true;
        }catch (e){
            // @ts-ignore
            console.log(e.response?.data?.message);
        }
    }

    async resetPassword(password:string,newPassword:string){
        try {
            const response =await AuthController.resetPassword(password,newPassword);
            if (typeof response.accessToken === "string" && response.user){
                localStorage.setItem("accessToken", response.accessToken);
                this.setAuth(true);
                this.setUser(response.user);
            }

        }catch (e){
            // @ts-ignore
            console.log(e.response?.data?.message);
        }
    }

    // Функция для получения куки по имени
    private  getCookie(name: string) {
        const matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"));
        return matches ? decodeURIComponent(matches[1]) : undefined;
    };


    //если пользователь авторизован, то у него будет сохранен refresh токен в cooke
    //пока срок годности refresh токена не истек, можно запросить новую пару токенов у сервера
    //иначе: localStorage.setItem('token',response.data.accessToken); установится в null
    //и в другом месте можно будет проверить авторизацию пользователя сделав   if(localStorage.getItem('token')) и в этом условии вызвать checkAuth()
    async checkAuth(){
        try {
            this.setLoading(true);
            const response:AuthResponse|null = await AuthController.refresh();

            if(response !=null && response.refreshToken!=null){
                localStorage.setItem('accessToken', response.accessToken);
                this.setAuth(true);
                this.setUser(response.user);
                return true;
            }

            const refreshToken = this.getCookie('refreshToken');

            if (response ==null || !refreshToken) {
                this.clearForNotAuth();
                return false;

            }

        }catch (e){
            console.log("Пользователь не авторизован или срок действия refresh токена истёк, пройдите авторизацию заново");
        }finally {
            this.setLoading(false);
        }
    };


   async getUserRoles(username:string):Promise<Roles> {
       return await AuthController.getUserRole(username);
   }

    private clearForNotAuth(){
            localStorage.removeItem('accessToken');
            this.setAuth(false);
            this.setUser({} as IUser);
    }

}