import React, {useEffect, useState} from 'react';

import './../../../css/style.css'
import './../../../css/bootstrap.min.css'
import './../../../css/magnify.css'
import './../../../css/ant107_shop.css'

// import "./../../../js/jquery.magnify.js"
import Product from "./product";
import DetailProduct from "../detail/product";
import Category from "../category";
import {ICardProduct} from "../../product/model/ICardProduct";
import ProductService from "../../product/service/productService";
import {ICategory} from "../../product/model/ICategory";
// import 'https://fonts.googleapis.com/icon?family=Material+Icons&#038;ver=5.0.3'
// import 'https://use.fontawesome.com/releases/v5.2.0/css/all.css?ver=5.0.3'
// import 'https://fonts.googleapis.com/css?family=Roboto:100,100italic,300,300italic,regular,italic,500,500italic,700,700italic,900,900italic&amp;subset=cyrillic'
//
// import '../../../js/jquery.js'
// import '../../../js/jquery-migrate.min.js'
// import '../../../js/bootstrap.min.js'



const Home=()=> {
    const [products,setProduct]=useState<ICardProduct[]>([]);

    async function getListProductOnHomePage(){
        try{
            const response =await ProductService.getCardsProduct();
            // @ts-ignore
            setProduct(response);

        }catch (e){
            // @ts-ignore
            console.log(e.response?.data?.message);
        }
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
                                    <Product products={products} />
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