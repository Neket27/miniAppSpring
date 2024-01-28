import React, {FC, useContext, useEffect} from 'react'
// import './App.css'

// import '../css/style.css'
// import '../css/bootstrap.min.css'
// import 'https://fonts.googleapis.com/icon?family=Material+Icons&#038;ver=5.0.3'
// import 'https://use.fontawesome.com/releases/v5.2.0/css/all.css?ver=5.0.3'
// import 'https://fonts.googleapis.com/css?family=Roboto:100,100italic,300,300italic,regular,italic,500,500italic,700,700italic,900,900italic&amp;subset=cyrillic'

import AuthRootComponent from "./auth";
import {Route, Routes} from "react-router-dom";
import {Context} from "./main";
import {observer} from "mobx-react-lite";
import Product from "./content/home/product";
import DetailProduct from "./content/detail/product";


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
                {/*<Route path="/detailProduct" element={<DetailProduct/>}></Route>*/}
                <Route path='/detailProduct/:typeId' element={<DetailProduct/>} />
            </Routes>
        </div>
    );

};

export default observer(App) //observer отслеживание изменения данных для mobx
