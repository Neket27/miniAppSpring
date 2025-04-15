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

const Stocks = () => {
    let context = useContext(ContextService)
    const location = useLocation();
    const [products, setProducts] = useState<ICardProduct[]>([]);


    async function getListProductOnHomePage() {
        const city:string = localStorage.getItem('city');
        if(city!="" && city!=null) {
            const productsCard = await context.discountService.getProductWithDiscountByTown(city);
            setProducts(productsCard);
        }
    }

    // async function fetchProductOnHomePage() {
    //     context.productService.connect((productsCart: ICardProduct[]) => {
    //         console.log("Полученные данные:", productsCart);
    //         setProducts(productsCart);
    //     });
    // }


    useEffect(() => {
        getListProductOnHomePage();
    }, [location]);

    //
    // useEffect(() => {
    //     fetchProductOnHomePage();
    // }, []);


    return (
        <div>
            <div id="ant107_shop" className="ant107_shop_container">
                <div className="container">
                    <div className="row">
                        <main className="col-xl-9 col-md-8">
                            <div className="ant107_shop-shop-items">
                                <ProductPageTodo products={products}></ProductPageTodo>

                            </div>
                        </main>
                        <Category OnClickGetListProduct={getListProductOnHomePage}/>
                    </div>
                </div>

            </div>
        </div>
    );
}

export default Stocks;