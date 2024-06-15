import React from "react";
import {ICardProduct} from "../../../product/model/ICardProduct";
import {Link} from "react-router-dom";

const Product=(props: { products: Array<ICardProduct> }) => {
    const cards= props.products.map((card:ICardProduct, index) => {

           return (<div key={card.id} className="col-lg-4 col-md-6">
                <div className="ant107_shop-shop-box">
                    <div className="ant107_shop-shop-img">
                        <Link to={"/detailProduct/" + card.id}>
                            <img src={"data:image/png;base64,"+card.imageDtoList.at(0)?.base64}
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
            </div>)
        }
    );

    return cards;
}
export default Product;