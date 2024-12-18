import {ICardProduct} from "../../../../model/product/ICardProduct";
import {Link} from "react-router-dom";
import React from "react";

const CartPreviewProduct=(props:{product:ICardProduct})=> {
    return (<div className="col-xl-3 col-lg-4 col-sm-6">
        <div className="ant107_shop-shop-box">
            <div className="ant107_shop-shop-img">
                <Link to={"/detailProduct/" + props.product.id}>
                    <img src={"data:image/png;base64," + props.product.imageDtoList.at(0)?.base64} alt=""/>
                </Link>
            </div>
            <div className="ant107_shop-shop-info">
                <h5><a href="">{props.product.name}</a></h5>
                <div className="ant107_shop-price-rating">
                    <span className="ant107_shop-shop-price">{props.product.cost}</span>
                    <span className="ant107_shop-shop-rating">{props.product.rating}</span>
                    <a href=""><i className="fas fa-shopping-cart"></i></a>
                </div>
            </div>
        </div>
    </div>);
}

export default CartPreviewProduct;