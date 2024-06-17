import FormForDetailProduct from "../../../form";
import {IDetailProduct} from "../../../../../model/product/IDetailProduct";

const Review =(props: {productDetail:IDetailProduct|undefined})=>{
    return (
        <div className="tab-pane" id="ant107_shop-review">
            <div className="ant107_shop-single-review">
                <div className="ant107_shop-reviewer-img">
                    <img src="/img/ant107_shop/avatar.png" alt=""/>
                </div>
                <div className="ant107_shop-reviewer">
                    <h6>Иван Дионтьев</h6>
                    <p>Точечное воздействие, на первый взгляд, традиционно усиливает
                        презентационный материал, полагаясь на инсайдерскую информацию.
                        SWOT-анализ последовательно охватывает продвигаемый формирование
                        имиджа.</p>
                </div>
                <div className="ant107_shop-reviewer-rating">
                    <span>15 мая 2030</span>
                    <div className="ant107_shop-ratings">
                        <i className="fas fa-star"></i>
                        <i className="fas fa-star"></i>
                        <i className="fas fa-star"></i>
                        <i className="fas fa-star"></i>
                        <i className="fas fa-star"></i>
                    </div>
                </div>
            </div>
            <div className="ant107_shop-single-review">
                <div className="ant107_shop-reviewer-img">
                    <img src="/img/ant107_shop/avatar2.png" alt=""/>
                </div>
                <div className="ant107_shop-reviewer">
                    <h6>Иван Дионтьев</h6>
                    <p>Точечное воздействие, на первый взгляд, традиционно усиливает
                        презентационный материал, полагаясь на инсайдерскую информацию.
                        SWOT-анализ последовательно охватывает продвигаемый формирование
                        имиджа.
                    </p>
                </div>
                <div className="ant107_shop-reviewer-rating">
                    <span>15 мая 2030</span>
                    <div className="ant107_shop-ratings">
                        <i className="fas fa-star"></i>
                        <i className="fas fa-star"></i>
                        <i className="fas fa-star"></i>
                        <i className="fas fa-star"></i>
                        <i className="fas fa-star"></i>
                    </div>
                </div>
            </div>
            <div className="ant107_shop-review-form mt-5">
                <div className="mb-4 text-center">
                    <h2>Написать отзыв</h2>
                </div>
                <p>gg</p>
                {// @ts-ignore
                <FormForDetailProduct idProduct={props.productDetail?.id}/>
                }
            </div>
        </div>
    );
}

export default Review;