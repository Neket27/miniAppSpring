import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import ProductService from "../../../product/service/productService";
import {IDetailProduct} from "../../../product/model/IDetailProduct";
const URL = import.meta.env.VITE_URL;

import "./../../../../css/magnify.css";
import "./../../../../css/ant107_shop.css";
import "./../../../../js/jquery.magnify.js"
import {ICardProduct} from "../../../product/model/ICardProduct";
import {ICategory} from "../../../product/model/ICategory";
import FormForDetailProduct from "../form";

const DetailProduct = () => {
    const {typeId} = useParams();
    const [productDetail, setProductDetail] = useState<IDetailProduct>();

    //дубляж функции с chooseCategory
    const [relatedProducts,setRelatedProducts]=useState<ICardProduct[]>([]);
    async function getProductByCategory(category:ICategory){
        const response = await ProductService.getProductsByCategory(category);
        console.log("res")
        console.log(response)
        // @ts-ignore
        setRelatedProducts(response);

    }

    async function getProductDetail() {
        try {
            // @ts-ignore
            const response = await ProductService.getProductDetail(parseInt(typeId, 10));
            // @ts-ignore
            setProductDetail(response);

        } catch (e) {
            // @ts-ignore
            console.log(e.response?.data?.message);
        }
    }

    useEffect(() => { // useEffect выполняется при первой загрузке или перезагрузки страницы
        getProductDetail();

    }, []);

    useEffect(() => { // useEffect выполняется при первой загрузке или перезагрузки страницы
        console.log("Категория")
        console.log(productDetail?.categoryProduct.stringValueCategory);
        let category:ICategory={
            // @ts-ignore
            categoryProduct:productDetail?.categoryProduct.stringValueCategory,
            subcategory:'unsupported',
            stringValueCategory:'mmm'
        }
        getProductByCategory(category)
    }, [productDetail]);


    const imagesMain = productDetail?.characteristicProduct.images.map(imageBytes=>
        <div className="tab-pane active" id="ant107_shop-preview2">
            <img src={"data:image/png;base64," +imageBytes } alt=""
                 data-magnify-src={"data:image/png;base64," +imageBytes}/>
        </div>
    );

    const images =productDetail?.characteristicProduct.images.map(imageBytes =>
        <li>
            <a data-toggle="tab" href="#ant107_shop-preview1">
                <img src={"data:image/png;base64,"+imageBytes} alt=""/>
            </a>
        </li>
    );

    const relatedProductListJsx = relatedProducts.map(product =>{
        console.log("product")
        console.log(product)
       return <div key={product.id} className="col-xl-3 col-lg-4 col-sm-6">
            <div className="ant107_shop-shop-box">
                <div className="ant107_shop-shop-img">
                    <a href="#!"><img src={URL+"/api/v1/home/get-image-with-media-type?id="+product.id} alt=""/></a>
                </div>
                <div className="ant107_shop-shop-info">
                    <h5><a href="#!">{product.name}</a></h5>
                    <div className="ant107_shop-price-rating">
                        <span className="ant107_shop-shop-price">{product.cost}</span>
                        <span className="ant107_shop-shop-rating">{product.rating}</span>
                        <a href="#"><i className="fas fa-shopping-cart"></i></a>
                    </div>
                </div>
            </div>
     </div>
    } );

    return (
        <div id="ant107_shop" className="ant107_shop_container">
            <div className="container">

                <main>
                    <div className="row">
                        <div className="col-lg-6">
                            <div className="ant107_shop-product-preview-wrap">
                                <div className="tab-content">
                                    <div className="tab-pane" id="ant107_shop-preview1">
                                        <img
                                            src={URL+"/api/v1/home/get-image-with-media-type?id=" + productDetail?.id}
                                            alt=""
                                            data-magnify-src={URL+"/api/v1/home/get-image-with-media-type?id=" + productDetail?.id}/>
                                    </div>

                                    {/*{imagesMain}*/}
                                    {/*<div className="tab-pane active" id="ant107_shop-preview2">*/}
                                    {/*    <img*/}
                                    {/*        src={"data:image/png;base64," + productDetail?.characteristicProduct.images[0]}*/}
                                    {/*        alt=""*/}
                                    {/*        data-magnify-src={"data:image/png;base64," + productDetail?.characteristicProduct.images[0]}/>*/}
                                    {/*</div>*/}

                                </div>

                                <ul className="nav nav-tabs d-flex align-content-between">
                                    {images}
                                </ul>
                            </div>
                        </div>
                        <div className="col-lg-6">
                            <div className="ant107_shop-product-details">
                                <h3 className="mb-3">{productDetail?.name}</h3>
                                <h6>Цена: <span>{productDetail?.cost}</span></h6>
                                <p>{productDetail?.detail}</p>
                                <h6>Бренд: <span>{productDetail?.brand}</span></h6>
                                <h6>Артикул: <span>{productDetail?.article}</span></h6>
                                <h6>Наличие: <span>{productDetail?.stock}</span></h6>
                                <h6>Участвует в акции: <span>{productDetail?.available?"Да":"Нет"}</span></h6>

                                <div className="ant107_shop-product-spinner mt-3">
                                    <div className="ant107_shop-number-input">
                                        <button className="ant107_shop-minus"></button>
                                        <input min="1" name="quantity" value="2" type="number"/>
                                        <button className="ant107_shop-plus"></button>
                                    </div>
                                    <a href="cart.html" className="ant107_shop-theme-btn ant107_shop-br-30 ml-3">В
                                        корзину</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="ant107_shop-product-details-review">
                        <ul className="nav nav-tabs mb-3 mt-3">
                            <li><a href="#ant107_shop-details" className="active" data-toggle="tab">Подробности</a>
                            </li>
                            <li><a href="#ant107_shop-review" data-toggle="tab" className="">Отзывы</a></li>
                        </ul>
                        <div className="tab-content">
                            <div className="tab-pane active" id="ant107_shop-details">
                                <p>{productDetail?.detail}</p>

                                <ul className="ant107_shop-list-style-one mt-3 mb-3">
                                    <li>{productDetail?.characteristicProduct.producerCountry}</li>
                                    <li>{productDetail?.characteristicProduct.sellerWarranty}</li>
                                </ul>
                                <p>Перераспределение бюджета, безусловно, обуславливает институциональный социальный
                                    статус. Основная стадия проведения рыночного исследования вырождена.
                                    Ретроконверсия национального наследия по-прежнему востребована. Медиавес основан
                                    на анализе телесмотрения.</p>
                            </div>
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
                                <div className="ant107_shop-review-form mt-5">
                                    <div className="mb-4 text-center">
                                        <h2>Написать отзыв</h2>
                                    </div>

                                   <FormForDetailProduct/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <hr className="mt-5"/>

                    <div className="ant107_shop-related-product mt-5">
                        <h3 className="mb-4">Похожие товары</h3>
                        <div className="row">

                            {relatedProductListJsx}

                        </div>
                    </div>
                </main>

            </div>
        </div>
    );

}


export default DetailProduct;