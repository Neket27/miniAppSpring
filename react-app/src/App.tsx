import React, {FC, useContext, useEffect, useState} from 'react'
import {Route, Routes} from "react-router-dom";
import {Context, State} from "./main";
import {observer} from "mobx-react-lite";
import DetailProduct from "./content/detail/product";
import ChooseCategory from "./content/chooseCategory";
import Cart from "./content/cart";
import Navbar from "./content/navbar";
import AuthRootComponent from "./content/auth";
import {Client, Message} from "@stomp/stompjs";
import SockJS from "sockjs-client";



const App:FC=()=> {
    const state:State= useContext(Context);
    const [orders, setOrders] = useState<Array<any>>([]);



    function addToOrder(item:any){
        // setState({orders:[...this.state.orders,item]})
        setOrders([orders,item])
        console.log(orders);
    }

    useEffect(()=>{ // useEffect выполняется при первой загрузке или перезагрузки страницы
       if(localStorage.getItem('token')) { //если пользователь авторизован, то у него есть токен
           state.authService.checkAuth();
       }
    }, []);

    if(state.authService.isLoading)
        return <div>Загрузка...</div>

    return (
        <div className="App">
            <Navbar />
            {/*<WebSocketComponent/>*/}
            {/*<ComponentsChat/>*/}
            <h1>{state.authService.isAuth?`Пользователь авторизован ${state.authService.user.email}`:'АВТОРИЗУЙТЕСЬ'}</h1>
            <div>{state.authService.isAuth?
                <button onClick={() => state.authService.logout()}>Выйти</button>:null
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
