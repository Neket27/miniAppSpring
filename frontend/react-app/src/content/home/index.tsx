import React, {useContext, useEffect, useState} from 'react';

import './../../../css/style.css'
import './../../../css/bootstrap.min.css'
import './../../../css/magnify.css'
import './../../../css/ant107_shop.css'
import Product from "./product";
import Category from "../category";
import {ICardProduct} from "../../model/product/ICardProduct";
import productService from "../../service/product/ProductService";
import {useLocation} from "react-router-dom";
import {ContextService} from "../../main";

const Home=()=> {
    let context = useContext(ContextService)
    const location = useLocation();
    const [products,setProduct]=useState<ICardProduct[]>([]);

    async function getListProductOnHomePage(){
        setProduct(await context.productService.getListProductOnHomePage())
    }

        useEffect(()=>{ // useEffect выполняется при первой загрузке или перезагрузки страницы
          getListProductOnHomePage();
    }, [location]);

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