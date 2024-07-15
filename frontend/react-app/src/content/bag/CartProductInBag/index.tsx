import {IProductBag} from "../../../model/product/IProductBag";

const CartProductInBag=(props:{product:IProductBag, accessToken:string|null, removeProductFromCart:any, handleDecrement:any,inputValues:any,handleInputChange:any,handleIncrement:any}) => {

    return <div className="ant107_shop-cart-single-item">
        <button type="button" className="close" onClick={() => {
            if (props.accessToken != null)
                props.removeProductFromCart(props.product.idProduct, props.accessToken);
        }}>
            <i className="fas fa-times"></i>
        </button>
        <div className="ant107_shop-product-img">
            <img src={"data:image/png;base64," + props.product.imageDtoList.at(0)?.base64} alt=""
                 style={{width: "100px", height: "100px"}}/>
        </div>
        <h5 className="ant107_shop-product-name">{props.product.name}</h5>
        <h5 className="ant107_shop-product-price">{props.product.cost}</h5>
        <div className="ant107_shop-number-input">
            <button className="ant107_shop-minus" onClick={() => props.handleDecrement(props.product.idProduct)}></button>
            <input key={props.product.idProduct} className="quantity" min="1" name="quantity"
                   value={props.inputValues[props.product.idProduct] || 0}
                   onChange={(e) => props.handleInputChange(props.product.idProduct, e.target.value)}
                   type="number"/>
            <button className="ant107_shop-plus" onClick={() => props.handleIncrement(props.product.idProduct)}></button>
        </div>
        <h5 className="ant107_shop-product-total-price">{props.product.cost * props.product.count}</h5>
    </div>;
}

export default CartProductInBag;