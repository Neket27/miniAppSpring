import React, {FC, useContext, useEffect, useState} from 'react'
import {Route, Routes} from "react-router-dom";
import {ContextService, State} from "./main";
import {observer} from "mobx-react-lite";
import DetailProduct from "./content/detail/product";
import ChooseCategory from "./content/chooseCategory";
import Bag from "./content/bag";
import Navbar, {ContextCountProductInBag} from "./content/navbar";
import AuthRootComponent from "./content/auth";
import CabinetUser from "./content/cabinetUser";
import ChangeDataUser from "./content/cabinetUser/changeDataUser";

const App:FC=()=> {
    const context:State= useContext(ContextService);
    const contextCountProductInBag =useContext(ContextCountProductInBag);
    const [orders, setOrders] = useState<Array<any>>([]);

    // function addToOrder(item:any){
    //     // setState({orders:[...this.context.orders,item]})
    //     setOrders([orders,item])
    //     console.log(orders);
    // }

    useEffect(()=>{ // useEffect выполняется при первой загрузке или перезагрузки страницы
       if(localStorage.getItem('accessToken')) { //если пользователь авторизован, то у него есть токен
           context.authService.checkAuth();
       }

    }, []);

    if(context.authService.isLoading) {
        return <div>Загрузка...</div>
    }

    if(context.authService.isAuth){
        contextCountProductInBag.getCountProductInBag();
    }


    return (
        <div className="App">
            <Navbar />
            {/*<h1 className="auth">{context.authService.isAuth?`Пользователь авторизован ${context.authService.user.email}`:'АВТОРИЗУЙТЕСЬ'}</h1>*/}
            {/*<div>{context.authService.isAuth?*/}
            {/*    <button onClick={async () => {*/}
            {/*        const logout = await context.authService.logout();*/}
            {/*        console.log("logout= "+logout)*/}
            {/*        if(logout == true)*/}
            {/*            contextCountProductInBag.setCountProductInBag(0);*/}
            {/*        window.location.reload();*/}
            {/*    }}>Выйти</button>:null*/}
            {/*     }*/}

            {/*</div>*/}
            <Routes>
                <Route path="/" element={<AuthRootComponent/>}></Route>
                <Route path="/login" element={<AuthRootComponent/>}></Route>
                <Route path="/register" element={<AuthRootComponent/>}></Route>
                <Route path="/cabinetUser" element={<CabinetUser/>}></Route>
                <Route path="/cabinetUser/chageData" element={<ChangeDataUser/>}></Route>
                <Route path="/reset" element={<AuthRootComponent/>}></Route>
                <Route path="/dashboard" element={<AuthRootComponent/>}></Route>
                <Route path='/detailProduct/:typeId' element={<DetailProduct />} />
                <Route path='/category/product/:typeId' element={<ChooseCategory/>} />
                <Route path='/cart' element={<Bag/>}/>
            </Routes>
        </div>
    );

};

export default observer(App) //observer отслеживание изменения данных для mobx
