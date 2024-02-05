import React, {createContext, useEffect, useState} from "react";
import ProductService from "../../../product/service/productService";
import {ICardProduct} from "../../../product/model/ICardProduct";
import {Link, useParams} from "react-router-dom";
import DetailProduct from "../../detail/product";
import Store from "../../../auth/store/Store";

const Product=(props: { products: any, click:any; }) => {
//     console.log("prop")
// console.log(props.products)
    // @ts-ignore
    const cards= props.products.map((card, index) =>
        // if (cart.categoryProduct == typeId)
            <div key={card.id} className="col-lg-4 col-md-6">

                <div className="ant107_shop-shop-box">
                    <div className="ant107_shop-shop-img">
                        <Link to={"/detailProduct/" + card.id}>
                            <img src={"http://localhost:8080/api/v1/home/get-image-with-media-type?id=" + card.id}
                                 alt=""/>
                        </Link>
                    </div>
                    <div className="ant107_shop-shop-info">
                        <h5><a href="shop-detail.html"></a>{card.name}</h5>
                        <div className="ant107_shop-price-rating">
                            <span className="ant107_shop-shop-price">{card.cost}</span>
                            <span className="ant107_shop-shop-rating">{card.rating}</span>
                            <a href="cart.html"><i className="fas fa-shopping-cart"></i></a>
                        </div>
                    </div>
                </div>
            </div>

    );

    return cards;
}
export default Product;