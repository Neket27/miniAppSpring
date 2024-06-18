import React, {useEffect, useState} from 'react';

import './../../../css/style.css'
import './../../../css/bootstrap.min.css'
import './../../../css/magnify.css'
import './../../../css/ant107_shop.css'
import Category from "../category";
import Product from "../home/product";
import {ICategory} from "../../model/product/ICategory";
import ProductController from "../../controller/ProductController";
import {ICardProduct} from "../../model/product/ICardProduct";
import {useParams} from "react-router-dom";
import translit from 'cyrillic-to-translit-js';
import {CardProductResponse} from "../../model/response/product/CardProductResponse";


const ChooseCategory=()=> {
    const [products,setProducts]=useState<ICardProduct[]>([]);
    async function getProductByCategory(category:ICategory){
        const response:CardProductResponse = await ProductController.getProductsByCategory(category);
        setProducts(response);
    }

    const {typeId}=useParams();

    useEffect(()=> {
        if (typeId != undefined) {
            let categoryOnRussian: string = translit().reverse(typeId);
            let category: ICategory = {
                categoryProduct: categoryOnRussian,
                subcategory: 'unsupported',
                stringValueCategory: 'mmm'
            }
        getProductByCategory(category);
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

                        <Category OnClickGetListProduct={getProductByCategory}  />

                    </div>
                </div>

            </div>
            {/*<button onClick={()=> getProductByCategory(ct)}></button>*/}
        </div>
    );
}

export default ChooseCategory;