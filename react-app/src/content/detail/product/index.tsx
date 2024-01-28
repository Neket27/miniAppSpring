import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import ProductService from "../../../product/service/productService";
import {IDetailProduct} from "../../../product/model/IDetailProduct";

import "./../../../../css/magnify.css";
import "./../../../../css/ant107_shop.css";
import "./../../../../js/jquery.magnify.js"
const DetailProduct = () => {
    const {typeId} = useParams();
    const [productDetail, setProductDetail] = useState<IDetailProduct>();

    async function getProductDetail() {
        try {
            // @ts-ignore
            const response = await ProductService.getProductDetail(parseInt(typeId, 10));
            // @ts-ignore
            setProductDetail(response);
    console.log(response)
        } catch (e) {
            // @ts-ignore
            console.log(e.response?.data?.message);
        }
    }

    useEffect(() => { // useEffect выполняется при первой загрузке или перезагрузки страницы
        getProductDetail();
    }, []);


    return (
        <div id="ant107_shop" className="ant107_shop_container">
            <div className="container">

                <main>
                    <div className="row">
                        <div className="col-lg-6">
                            <div className="ant107_shop-product-preview-wrap">
                                <div className="tab-content">
                                    <div className="tab-pane" id="ant107_shop-preview1">
                                        <img src="/img/ant107_shop/img02.jpg" alt=""
                                             data-magnify-src="/img/ant107_shop/img02.jpg"/>
                                    </div>
                                    <div className="tab-pane active" id="ant107_shop-preview2">
                                        <img src="/img/ant107_shop/img03.jpg" alt=""
                                             data-magnify-src="/img/ant107_shop/img03.jpg"/>
                                    </div>
                                    <div className="tab-pane" id="ant107_shop-preview3">
                                        <img src="/img/ant107_shop/img04.jpg" alt=""
                                             data-magnify-src="/img/ant107_shop/img04.jpg"/>
                                    </div>
                                    <div className="tab-pane" id="ant107_shop-preview4">
                                        <img src="/img/ant107_shop/img05.jpg" alt=""
                                             data-magnify-src="/img/ant107_shop/img05.jpg"/>
                                    </div>
                                </div>

                                <ul className="nav nav-tabs d-flex align-content-between">
                                    <li>
                                        <a data-toggle="tab" href="#ant107_shop-preview1">
                                            <img src="/img/ant107_shop/img02.jpg" alt=""/>
                                        </a>
                                    </li>
                                    <li>
                                        <a className="active" data-toggle="tab" href="#ant107_shop-preview2">
                                            <img src="/img/ant107_shop/img03.jpg" alt=""/>
                                        </a>
                                    </li>
                                    <li>
                                        <a data-toggle="tab" href="#ant107_shop-preview3">
                                            <img src="/img/ant107_shop/img04.jpg" alt=""/>
                                        </a>
                                    </li>
                                    <li>
                                        <a data-toggle="tab" href="#ant107_shop-preview4">
                                            <img src="/img/ant107_shop/img05.jpg" alt=""/>
                                        </a>
                                    </li>
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
                                    {/*<li>{productDetail?.characteristic.producerCountry}</li>*/}
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

                                    <form id="ant107_shop-contact-form" className="ant107_shop-contact-form"
                                          action="#" method="POST">
                                        <div className="row clearfix">
                                            <div className="col-md-6">
                                                <div className="form-group">
                                                    <label htmlFor="ant107_shop-name">Ваше имя</label>
                                                    <input type="text" name="first_name" id="ant107_shop-name"
                                                           className="form-control" value=""
                                                           placeholder="Иван Дионтьев" required/>
                                                </div>
                                            </div>
                                            <div className="col-md-6">
                                                <div className="form-group">
                                                    <label htmlFor="email">Email</label>
                                                    <input type="ant107_shop-email" name="email"
                                                           id="ant107_shop-email" className="form-control" value=""
                                                           placeholder="yourmail@gmail.com" required/>
                                                </div>
                                            </div>
                                            <div className="col-md-12">
                                                <div className="form-group">
                                                    <label htmlFor="ant107_shop-message">Сообщение</label>
                                                    <textarea name="message" id="ant107_shop-message"
                                                              className="form-control" rows={3}
                                                              placeholder="Что Вы хотите сказать"
                                                              required></textarea>
                                                </div>
                                            </div>
                                            <div className="col-md-4 d-flex">
                                                <div className="ant107_shop-your-rating d-flex align-items-center">
                                                    <h6 className="mb-0 mr-2">Ваша оценка:</h6>
                                                    <div className="ant107_shop-ratings"
                                                         id="ant107_shop-your-rating">
                                                        <i className="fas fa-star"></i>
                                                        <i className="fas fa-star"></i>
                                                        <i className="fas fa-star"></i>
                                                        <i className="fas fa-star"></i>
                                                        <i className="fas fa-star"></i>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="col-md-4">
                                                <div className="ant107_shop-upload-btn-wrapper">
                                                    <button className="ant107_shop-upload-btn">
                                                        <span><i className="fas fa-file-image"></i>Вложения</span>
                                                    </button>
                                                    <input type="file" name="myfile"/>
                                                </div>
                                            </div>
                                            <div className="col-md-4">
                                                <div className="form-group text-left text-md-right mb-0">
                                                    <button className="ant107_shop-theme-btn ant107_shop-br-30"
                                                            type="submit">Отправить
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <hr className="mt-5"/>

                    <div className="ant107_shop-related-product mt-5">
                        <h3 className="mb-4">Похожие товары</h3>
                        <div className="row">
                            <div className="col-xl-3 col-lg-4 col-sm-6">
                                <div className="ant107_shop-shop-box">
                                    <div className="ant107_shop-shop-img">
                                        <a href="#!"><img src="/img/ant107_shop/img02.jpg" alt=""/></a>
                                    </div>
                                    <div className="ant107_shop-shop-info">
                                        <h5><a href="#!">Название товара</a></h5>
                                        <div className="ant107_shop-price-rating">
                                            <span className="ant107_shop-shop-price">650.00</span>
                                            <span className="ant107_shop-shop-rating">4.0</span>
                                            <a href="#"><i className="fas fa-shopping-cart"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="col-xl-3 col-lg-4 col-sm-6">
                                <div className="ant107_shop-shop-box">
                                    <div className="ant107_shop-shop-img">
                                        <a href="#!"><img src="/img/ant107_shop/img03.jpg" alt=""/></a>
                                    </div>
                                    <div className="ant107_shop-shop-info">
                                        <h5><a href="#!">Название товара</a></h5>
                                        <div className="ant107_shop-price-rating">
                                            <span className="ant107_shop-shop-price">650.00</span>
                                            <span className="ant107_shop-shop-rating">4.0</span>
                                            <a href="#"><i className="fas fa-shopping-cart"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="col-xl-3 col-lg-4 col-sm-6">
                                <div className="ant107_shop-shop-box">
                                    <div className="ant107_shop-shop-img">
                                        <a href="#!"><img src="/img/ant107_shop/img04.jpg" alt=""/></a>
                                    </div>
                                    <div className="ant107_shop-shop-info">
                                        <h5><a href="#!">Название товара</a></h5>
                                        <div className="ant107_shop-price-rating">
                                            <span className="ant107_shop-shop-price">650.00</span>
                                            <span className="ant107_shop-shop-rating">4.0</span>
                                            <a href="#"><i className="fas fa-shopping-cart"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="col-xl-3 col-lg-4 col-sm-6">
                                <div className="ant107_shop-shop-box">
                                    <div className="ant107_shop-shop-img">
                                        <a href="#!"><img src="/img/ant107_shop/img05.jpg" alt=""/></a>
                                    </div>
                                    <div className="ant107_shop-shop-info">
                                        <h5><a href="#!">Название товара</a></h5>
                                        <div className="ant107_shop-price-rating">
                                            <span className="ant107_shop-shop-price">650.00</span>
                                            <span className="ant107_shop-shop-rating">4.0</span>
                                            <a href="#"><i className="fas fa-shopping-cart"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>

            </div>
        </div>
    );

}


export default DetailProduct;