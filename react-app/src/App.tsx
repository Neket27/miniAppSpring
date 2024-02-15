import React, {FC, useContext, useEffect, useState} from 'react'
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
import DetailProduct from "./content/detail/product";
import ChooseCategory from "./content/chooseCategory";
import Cart from "./content/cart";
import Navbar from "./content/navbar";
import CartController from "./content/cart/controller/CartController";
import WebSocketComponent from "./content/WebSocketComponent";
import ComponentsChat from "./componentsChat";

// // @ts-ignore
// const CountProductContext = React.createContext();
const App:FC=()=> {
    const {store} = useContext(Context);
    const [orders, setOrders] = useState<Array<any>>([]);



    function addToOrder(item:any){
        // setState({orders:[...this.state.orders,item]})
        setOrders([orders,item])
        console.log(orders);
    }

    useEffect(()=>{ // useEffect выполняется при первой загрузке или перезагрузки страницы
       if(localStorage.getItem('token')) { //если пользователь авторизован, то у него есть токен
           store.checkAuth();
       }
    }, []);

    if(store.isLoading)
        return <div>Загрузка...</div>

    return (
        <div className="App">
            {/*<Navbar />*/}
            {/*<WebSocketComponent/>*/}
            <ComponentsChat/>
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
                <Route path='/detailProduct/:typeId' element={<DetailProduct />} />
                <Route path='/category/product/:typeId' element={<ChooseCategory/>} />
                <Route path='/cart' element={<Cart/>}/>
            </Routes>
        </div>
    );

};

export default observer(App) //observer отслеживание изменения данных для mobx
