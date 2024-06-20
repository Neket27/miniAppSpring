import React from 'react';
import {Link} from 'react-router-dom';
import Home from "../home";
import LoginPage from "./login";
import RegisterPage from "./registreition";
import ResetPassword from "./resetPassword";
import Dashboard from "./dashboard";

const Path =()=>{
    return (
        window.location.pathname==='/'?
            <Home/>:
            window.location.pathname==='/login'?
                <LoginPage/>:
                location.pathname==='/register'?
                    <RegisterPage/>:
                    location.pathname==='/reset'?
                        <ResetPassword/>:
                        location.pathname==='/dashboard'?
                                <Dashboard /> :null
    );
}
const AuthRootComponent=()=>{
    return(
        <div>
            <nav>
                <ul>
                    <li>
                        <Link to="/">Home</Link>
                    </li>
                    <li>
                        <Link to="/login">Login</Link>
                    </li>
                    <li>
                        <Link to="/register">Register</Link>
                    </li>
                    <li>
                        <Link to="/reset">Reset</Link>
                    </li>
                    <li>
                        <Link to="/dashboard">Dashboard</Link>
                    </li>
                </ul>
            </nav>
            <Path/>
        </div>

    );


};

export default AuthRootComponent;
