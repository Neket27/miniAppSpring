import React, {FC, useContext, useEffect, useState} from 'react'
import AuthRootComponent from "./auth";
import {Route, Routes} from "react-router-dom";
import {Context} from "./main";
import {observer} from "mobx-react-lite";
import DetailProduct from "./content/detail/product";
import ChooseCategory from "./content/chooseCategory";
import Cart from "./content/cart";
import Navbar from "./content/navbar";
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
            <Navbar />
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
