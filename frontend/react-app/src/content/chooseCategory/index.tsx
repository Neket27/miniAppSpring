import React, {useContext, useEffect, useState} from 'react';

import './../../../css/style.css'
import './../../../css/bootstrap.min.css'
import './../../../css/magnify.css'
import './../../../css/ant107_shop.css'
import Category from "../category";
import Product from "../home/product";
import {ICardProduct} from "../../model/product/ICardProduct";
import {useParams} from "react-router-dom";
import translit from 'cyrillic-to-translit-js';
import {ContextService} from "../../main";


const ChooseCategory=()=> {
    const context = useContext(ContextService);
    const [products,setProducts]=useState<Array<ICardProduct>>();

    async function getProductsByCategory(category:string){
        const response:Array<ICardProduct> = await context.productService.getProductsByCategory(category);
        console.log("response= "+response[0].name);

        setProducts(response);
    }

    const {typeId}=useParams();

    useEffect(()=> {
        if (typeId != undefined) {
            let category:string;
                if(typeId=="allCategories"){
                    category= 'Все категории';
                }else {
                    let categoryOnRussian: string = translit().reverse(typeId);
                    category = categoryOnRussian;
                }
                    getProductsByCategory(category);

    }
},[])

    return (
        <div>
            <div id="ant107_shop" className="ant107_shop_container">
                <div className="container">
                    <div className="row" >

                        <main className="col-xl-9 col-md-8">
                            <div className="ant107_shop-shop-items">
                                <div className="row">
                                    {// @ts-ignore
                                    <Product products={products} />
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

                        <Category OnClickGetListProduct={getProductsByCategory}  />

                    </div>
                </div>

            </div>
        </div>
    );
}

export default ChooseCategory;