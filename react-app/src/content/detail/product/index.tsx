
import {useEffect, useState} from "react";
import {Link, NavLink, Route, Router, useParams, useNavigate, useLocation} from "react-router-dom";
import ProductService from "../../../product/service/productService";
import {IDetailProduct} from "../../../product/model/IDetailProduct";
const URL = import.meta.env.VITE_URL;

import "./../../../../css/magnify.css";
import "./../../../../css/ant107_shop.css";
import "./../../../../js/jquery.magnify.js"
import {ICardProduct} from "../../../product/model/ICardProduct";
import {ICategory} from "../../../product/model/ICategory";
import FormForDetailProduct from "../form";
import Review from "./swithBlocks/review";
import Detail from "./swithBlocks/detail";
import {IProductCart} from "../../../product/model/IProductCart";
import CartController from "../../cart/controller/CartController";
import {ProductCartResponse} from "../../../product/model/response/ProductCartResponse";

const DetailProduct = () => {
    const location = useLocation();
    const { typeId } = useParams<{ typeId: string }>();
    const [productDetail, setProductDetail] = useState<IDetailProduct | null>(null);
    const [countProductsInBag, setCountProductsInBag] = useState<number>(0);

    const [showBlockDetail, setShowBlockDetail] = useState<boolean>(true);
    const [showBlockReview, setShowBlockReview] = useState<boolean>(false);
    const [productFromCart, setProductFromCart] = useState<ProductCartResponse | null>(null);
    const [titleCart, setTitleCart] = useState<string>('');
    const [relatedProducts, setRelatedProducts] = useState<ICardProduct[]>([]);

    async function getProductByCategory(category: string) {
        try {
            const response = await ProductService.getProductsByCategory({ categoryProduct: category, subcategory: 'unsupported', stringValueCategory: 'mmm' });
            setRelatedProducts(response);
        } catch (error) {
            console.error("Error fetching related products:", error);
        }
    }

    async function getProductDetail() {
        try {
            const response = await ProductService.getProductDetail(parseInt(typeId, 10));
            setProductDetail(response);
        } catch (error) {
            console.error("Error fetching product detail:", error);
        }
    }

    async function getProductFromCart(idProduct: number, accessToken: string | null) {
        try {
            const response = await CartController.getProductFromCart(idProduct, accessToken);
            setProductFromCart(response);
        } catch (error) {
            console.error("Error fetching product from cart:", error);
        }
    }
    const sendCountProductInCart = async (idProduct: number, count: number, accessToken: string) => {
        if (!isNaN(count)) {  // Проверка на то, что count является числом
            const response = await CartController.sendCountProductInCart(idProduct, count, accessToken);
        } else {
            console.error("Ошибка: Невалидное значение countProductsInBag");
        }
    };

    useEffect(() => { // useEffect выполняется при первой загрузке или перезагрузки страницы
        getProductDetail();

    }, []);

    useEffect(() => { // useEffect выполняется при первой загрузке или перезагрузки страницы

        if (productDetail) {
            getProductByCategory(productDetail.categoryProduct.stringValueCategory);
            getProductFromCart(productDetail.id, localStorage.getItem('token'));
        }

    }, [productDetail]);

    const handleBeforeUnload = () => {
        console.log("countProductsInBagL = "+(countProductsInBag));
        // Выполните здесь необходимые действия перед выгрузкой страницы, например, отправку данных на сервер
        if (countProductsInBag > 0) {
            setTitleCart("Добавлен в корзину");
            const productCard = {
                idProduct: productDetail?.id,
                accessToken: localStorage.getItem('token'),
                count: countProductsInBag,
                showInCart: true
            };
            if (productFromCart?.count!=undefined){
                sendCountProductInCart(productDetail?.id, countProductsInBag, localStorage.getItem('token'));
            }else {
                CartController.addProductInCart(productCard);
                sendCountProductInCart(productDetail?.id, countProductsInBag, localStorage.getItem('token'));
                setProductFromCart(productCard);
            }


        }else {
            if(productFromCart?.count!=undefined) {
                CartController.removeProductFromCart(productDetail?.id, localStorage.getItem('token'));
                setProductFromCart('');
            }
            if(countProductsInBag<1)
                setTitleCart("В корзину");

        }
        console.log("SEEEEEEEEEEEND")
    };

    const handleRefresh = () => {
        window.location.reload();
    };


    useEffect(() => {
        // productFromCart?.count!=undefined?setCountProductsInBag(productFromCart?.count):setCountProductsInBag(0);
        if (productFromCart?.count != undefined) {
            setTitleCart("Добавлен в корзину");
            setCountProductsInBag(productFromCart?.count);
        } else {
            setTitleCart("В корзину");
            setCountProductsInBag(0);
        }


    }, [productDetail, productFromCart]);


    useEffect(() => {


        const unlisten = () => {
            // Вы можете выполнить нужные действия при изменении маршрута здесь
            console.log('Маршрут изменен:', location.pathname);
            handleBeforeUnload(); // Вызов функции перед переходом на другой URL
        };

        return unlisten;
    }, [countProductsInBag, productDetail,location.pathname]);


    useEffect(() => {
        const handleBeforeUnload = () => {
            // Выполните здесь необходимые действия перед переходом на другой URL
            if (countProductsInBag > 0) {
                const productCard = {
                    idProduct: productDetail?.id,
                    accessToken: localStorage.getItem('token'),
                    count: countProductsInBag,
                    showInCart: true
                };
                CartController.addProductInCart(productCard);
                sendCountProductInCart(productDetail?.id, countProductsInBag, localStorage.getItem('token'));
                console.log("Данные о количестве товаров отправлены на сервер перед переходом на другой URL");

            }
        };


        window.addEventListener('beforeunload', handleBeforeUnload);
        return ()=>{
            window.removeEventListener('beforeunload', handleBeforeUnload);
        }
    }, [countProductsInBag, productDetail]);


    const imagesMain = productDetail?.characteristicProduct.images.map(imageBytes=>
        <div className="tab-pane active" id="ant107_shop-preview2">
            <img src={"data:image/png;base64," +imageBytes } alt=""
                 data-magnify-src={"data:image/png;base64," +imageBytes}/>
        </div>
    );

    const images =productDetail?.characteristicProduct.images.map(imageBytes =>
        <li>
            {/*<a data-toggle="tab" href="#ant107_shop-preview1">*/}
            <img src={"data:image/png;base64,"+imageBytes} alt=""/>
            {/*</a>*/}
        </li>
    );

    const relatedProductListJsx = relatedProducts.map(product =>{
        return <div key={product.id} className="col-xl-3 col-lg-4 col-sm-6">
            <div className="ant107_shop-shop-box">
                <div className="ant107_shop-shop-img">
                    {/*<a href="#!">*/}
                    <img src={URL+"/api/v1/home/get-image-with-media-type?id="+product.id} alt=""/>
                    {/*</a>*/}
                </div>
                <div className="ant107_shop-shop-info">
                    <h5><a href="">{product.name}</a></h5>
                    <div className="ant107_shop-price-rating">
                        <span className="ant107_shop-shop-price">{product.cost}</span>
                        <span className="ant107_shop-shop-rating">{product.rating}</span>
                        <a href=""><i className="fas fa-shopping-cart"></i></a>
                    </div>
                </div>
            </div>
        </div>
    } );



    const showDetail = () => {
        setShowBlockDetail(false);
        setShowBlockReview(true);
    };

    const showReview= () => {
        setShowBlockDetail(true);
        setShowBlockReview(false);
    };
    console.log("productFromCart?.count="+productFromCart?.count)
    console.log("countProductInBag= "+countProductsInBag)



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
                                        <button className="ant107_shop-minus" onClick={()=>{
                                            setCountProductsInBag(prevCount => {

                                                const newCount = Math.max(prevCount - 1, 0);
                                                handleBeforeUnload(newCount);
                                                console.log("countProductInBag= " + newCount);
                                                return newCount;
                                            });

                                        }}></button>
                                        <input min="1" max="50" name="quantity" value={countProductsInBag}  type="number"/>
                                        <button className="ant107_shop-plus" onClick={()=>{
                                            setCountProductsInBag(prevCount => {
                                                const newCount = prevCount + 1;
                                                handleBeforeUnload(newCount);
                                                return newCount;
                                            });
                                        }} ></button>
                                    </div>
                                    {//@ts-ignore
                                        productFromCart?.count==undefined  ?
                                            <div onClick={() => {
                                                // @ts-ignore
                                                localStorage.setItem('countProductInCart',parseInt(localStorage.getItem('countProductInCart'))+1);
                                                console.log("add "+1);
                                                // CartController.addProductInCart(productCard);
                                                setCountProductsInBag(1);
                                                handleBeforeUnload();
                                                // @ts-ignore
                                                // getProductFromCart(productDetail.id,localStorage.getItem('token'))
                                            }} className="ant107_shop-theme-btn ant107_shop-br-30 ml-3">{titleCart}</div> :


                                            <Link  to="/cart">
                                                <div className="ant107_shop-theme-btn ant107_shop-br-30 ml-3" >
                                                    {titleCart}
                                                </div>
                                            </Link>
                                    }

                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="ant107_shop-product-details-review">
                        <ul className="nav nav-tabs mb-3 mt-3">
                            <li><a className={showBlockDetail ? "active" : ""} onClick={showReview}>Подробности</a>
                            </li>
                            <li><a className={showBlockReview ? "active" : ""} onClick={showDetail}>Отзывы</a></li>
                        </ul>
                        <div className="tab-content">
                            <div>
                                {showBlockDetail && <Detail productDetail={productDetail}/>}
                                {showBlockReview && <Review productDetail={productDetail}/>}
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
