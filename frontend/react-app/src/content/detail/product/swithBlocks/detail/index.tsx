import {IDetailProduct} from "../../../../../model/product/IDetailProduct";

const Detail = (props: {productDetail:IDetailProduct|undefined}) => {

    return (
        <div className="tab-pane active" id="ant107_shop-details">
            <p>{props.productDetail?.detail}</p>

            <ul className="ant107_shop-list-style-one mt-3 mb-3">
                <li>{props.productDetail?.characteristicProductDto.producerCountry}</li>
                <li>{props.productDetail?.characteristicProductDto.sellerWarranty}</li>
            </ul>
            <p>

                {props.productDetail?.description}
            </p>
        </div>
    );
}

export default Detail;