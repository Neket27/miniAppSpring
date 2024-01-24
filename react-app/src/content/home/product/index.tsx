import React, {useEffect, useState} from "react";
import ProductService from "../../../product/service/productService";

const Product=() => {
    const [products,setProduct]=useState<any>([]);

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
        console.log(products);
    }, []);
    // @ts-ignore
    const cards= products.map((card, index) =>

    <div key={index} className="col-lg-4 col-md-6">
        <div className="ant107_shop-shop-box">
            <div className="ant107_shop-shop-img">
                <a href="shop-detail.html">
                    <img src="/img/ant107_shop/img02.jpg" alt=""/>
                </a>
            </div>
            <div className="ant107_shop-shop-info">
                <h5><a href="shop-detail.html"></a>{card.name}</h5>
                <div className="ant107_shop-price-rating">
                    <span className="ant107_shop-shop-price">{card.cost}</span>
                    <span className="ant107_shop-shop-rating">5.0</span>
                    <a href="cart.html"><i className="fas fa-shopping-cart"></i></a>
                </div>
            </div>
        </div>
    </div>
    );

    return cards;
}
export default Product;