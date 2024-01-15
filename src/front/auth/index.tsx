import {useLocation} from "react-router-dom";
import LoginPage from "./login";
import RegisterPage from "./registreition";

const AuthRootComponent=()=>{
    const location:any =useLocation();
    return (location.pathname==='/login'?<LoginPage/>:location.pathname==='/register'?<RegisterPage/>:null);
};

export default AuthRootComponent;