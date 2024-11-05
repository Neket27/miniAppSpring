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
import {ProductAdd} from "./content/detail/product/addProduct";
import {UpdateProduct} from "./content/detail/product/updateProduct";
import {DeleteProduct} from "./content/detail/product/deleteProduct";
import {AddCoupon} from "./content/coupon/coupunAdd";
import {DiscountAdd} from "./content/discount/discountAdd";
import AboutUs from "./content/aboutUs";
import SupportPage from "./content/supportPage";
import DeliveryUserData from "./content/cabinetUser/delivery";

const App:FC=()=> {
    const context:State= useContext(ContextService);
    const contextCountProductInBag =useContext(ContextCountProductInBag);

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
            <Routes>
                <Route path="/" element={<AuthRootComponent/>}></Route>
                <Route path="/login" element={<AuthRootComponent/>}></Route>
                <Route path="/register" element={<AuthRootComponent/>}></Route>
                <Route path="/cabinetUser" element={<CabinetUser/>}></Route>
                <Route path="/cabinetUser/changeData" element={<ChangeDataUser/>}></Route>
                <Route path="/resetPassword" element={<AuthRootComponent/>}></Route>
                <Route path="/dashboard" element={<AuthRootComponent/>}></Route>
                <Route path='/detailProduct/:typeId' element={<DetailProduct />} />
                <Route path='/category/product/:typeId' element={<ChooseCategory/>} />
                <Route path='/cart' element={<Bag/>}/>
                <Route path='/product/add' element={<ProductAdd/>}/>
                <Route path='/product/update' element={<UpdateProduct/>}/>
                <Route path='/product/delete' element={<DeleteProduct/>}/>
                <Route path='/coupon/add' element={<AddCoupon/>}/>
                <Route path='/discount/create' element={<DiscountAdd/>}/>
                <Route path='/aboutUs' element={<AboutUs/>}/>
                <Route path='/help' element={<SupportPage/>}/>
                <Route path= '/cabinetUser/delivery' element={<DeliveryUserData/>}/>
            </Routes>
        </div>
    );

};

export default observer(App) //observer отслеживание изменения данных для mobx
