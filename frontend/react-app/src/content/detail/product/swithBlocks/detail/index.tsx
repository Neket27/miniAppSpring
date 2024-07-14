import {IDetailProduct} from "../../../../../model/product/IDetailProduct";

const Detail = (props: {productDetail:IDetailProduct|undefined}) => {

    return (
        <div className="tab-pane active" id="ant107_shop-details">
            <p>{props.productDetail?.detail}</p>

            <ul className="ant107_shop-list-style-one mt-3 mb-3">
                <li>{props.productDetail?.characteristicProductDto.producerCountry}</li>
                <li>{props.productDetail?.characteristicProductDto.sellerWarranty}</li>
            </ul>
            <p>Перераспределение бюджета, безусловно, обуславливает институциональный социальный
                статус. Основная стадия проведения рыночного исследования вырождена.
                Ретроконверсия национального наследия по-прежнему востребована. Медиавес основан
                на анализе телесмотрения.
            </p>
        </div>
    );
}

export default Detail;