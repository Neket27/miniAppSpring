import React, {FC, useContext, useEffect} from 'react'
import './App.css'
import AuthRootComponent from "./auth";
import {Route, Routes} from "react-router-dom";
import {Context} from "./main";
import {observer} from "mobx-react-lite";


const App:FC=()=> {
    const {store} = useContext(Context);

    useEffect(()=>{ // useEffect выполняется при первой загрузке или перезагрузки страницы
       if(localStorage.getItem('token')) { //если пользователь авторизован, то у него есть токен
           store.checkAuth();
       }
    }, []);

    if(store.isLoading)
        return <div>Загрузка...</div>

    return (
        <div className="App">
            <h1>{store.isAuth?`Пользователь авторизован ${store.user.email}`:'АВТОРИЗУЙТЕСЬ'}</h1>
            <div>{store.isAuth?
                <button onClick={() => store.logout()}>Выйти</button>:null
                 }
            </div>

            <Routes>
                <Route path="/" element={<AuthRootComponent/>}></Route>
                <Route path="/login" element={<AuthRootComponent/>}></Route>
                <Route path="/register" element={<AuthRootComponent/>}></Route>
                <Route path="/reset" element={<AuthRootComponent/>}></Route>
                <Route path="/dashboard" element={<AuthRootComponent/>}></Route>
            </Routes>
        </div>
    );

};

export default observer(App) //observer отслеживание изменения данных для mobx
