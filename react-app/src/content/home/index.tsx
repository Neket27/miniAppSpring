import React, {useEffect, useState} from 'react';

import './../../../css/style.css'
import './../../../css/bootstrap.min.css'
import './../../../css/magnify.css'
import './../../../css/ant107_shop.css'
import Product from "./product";
import Category from "../category";
import {ICardProduct} from "../../model/product/ICardProduct";
import ProductController from "../../controller/ProductController";
import ProductService from "../../service/product/ProductService";


const Home=()=> {
    const [products,setProduct]=useState<ICardProduct[]>([]);

    // async function getListProductOnHomePage(){
    //     try{
    //         const response:ICardProduct[] = await ProductController.getCardsProduct();
    //         setProduct(response);
    //
    //     }catch (e){
    //         // @ts-ignore
    //         console.log("Ошибка получения данных в getListProductOnHomePage() = "+response);
    //     }
    // }
    async function getListProductOnHomePage(){
        setProduct(await ProductService.getListProductOnHomePage())
    }

        useEffect(()=>{ // useEffect выполняется при первой загрузке или перезагрузки страницы
          getListProductOnHomePage();
    }, []);

    return (
        <div>
            <div id="ant107_shop" className="ant107_shop_container">
                <div className="container">
                    <div className="row">
                        <main className="col-xl-9 col-md-8">
                            <div className="ant107_shop-shop-items">
                                <div className="row">
                                    {// @ts-ignore
                                    <Product products={products}  />
                                    }
                                </div>
                                <nav className="ant107_shop-pazination mt-4">
                                    <ul>
                                        <li className="active"><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </main>
                        <Category OnClickGetListProduct={getListProductOnHomePage}/>
                    </div>
                </div>

            </div>
        </div>
    );
}

export default Home;