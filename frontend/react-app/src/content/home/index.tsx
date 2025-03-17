import React, {useContext, useEffect, useState} from 'react';

import './../../../css/style.css'
import './../../../css/bootstrap.min.css'
import './../../../css/magnify.css'
import './../../../css/ant107_shop.css'
import Category from "../category";
import {ICardProduct} from "../../model/product/ICardProduct";
import {useLocation} from "react-router-dom";
import {ContextService} from "../../main";
import {ProductPageTodo} from "../detail/product/pageTodo";
import context from "react-bootstrap/NavbarContext";
import NeuralNetworkDialog from "../chatWithNeiro/NeuralNetworkDialog";

const Home=()=> {
    let context = useContext(ContextService)
    const location = useLocation();
    const [products,setProducts]=useState<ICardProduct[]>([]);


    async function getListProductOnHomePage(){
        setProducts(await context.productService.getListProductOnHomePage())
    }

    async function fetchProductOnHomePage(){
        context.productService.connect((productsCart: ICardProduct[]) => {
            console.log("Полученные данные:", productsCart);
            setProducts(productsCart);  // Обновляем состояние с новыми данными
        });
    }


        useEffect(()=>{
          getListProductOnHomePage();
    }, [location]);

    // useEffect(() => {
    //    fetchProductOnHomePage();
    // }, [products]);

    useEffect(() => {
        fetchProductOnHomePage();
    }, []);



    return (
        <div>
            <NeuralNetworkDialog
                show={true}
                handleClose={false}
                onSelectProducts={setProducts}
            />
            <div id="ant107_shop" className="ant107_shop_container">
                <div className="container">
                    <div className="row">
                        <main className="col-xl-9 col-md-8">
                            <div className="ant107_shop-shop-items">
                                    <ProductPageTodo products ={products}></ProductPageTodo>

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