import {useEffect, useState} from "react";
import {Link, NavLink, Route, Router, useParams} from "react-router-dom";
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
    const {typeId} = useParams();
    const [productDetail, setProductDetail] = useState<IDetailProduct>();
    const [countProductsInBag, setCountProductsInBag] = useState<number>(0);

    const [showBlockDetail, setShowBlockDetail] = useState(true);
    const [showBlockReview, setShowBlockReview] = useState(false);
    const [productFromCart, setProductFromCart] = useState<ProductCartResponse>();
    const [titleCart, setTitleCart] = useState<string>('');

    //дубляж функции с chooseCategory
    const [relatedProducts,setRelatedProducts]=useState<ICardProduct[]>([]);
    async function getProductByCategory(category:ICategory){
        const response = await ProductService.getProductsByCategory(category);
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

    async function getProductFromCart(idProduct:number,accessToken:string){
        console.log("getProductFromCart")
        try {
            // @ts-ignore
            const response = await CartController.getProductFromCart(idProduct,accessToken);
            console.log("response-getProductFromCart: ", response);
            setProductFromCart(response);
        } catch (error) {
            console.error("Error:", error);
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
        console.log("Категория")
        console.log(productDetail?.categoryProduct.stringValueCategory);
        let category:ICategory={
            // @ts-ignore
            categoryProduct:productDetail?.categoryProduct.stringValueCategory,
            subcategory:'unsupported',
            stringValueCategory:'mmm'
        }
        getProductByCategory(category)

        // @ts-ignore
        const product:ProductCartResponse = getProductFromCart(productDetail?.id,localStorage.getItem('token'))
        console.log("useEffectProduct= "+product)
        setProductFromCart(product);
        // setCountProductsInBag(product.count);

    }, [productDetail]);

    useEffect(()=>{
        // @ts-ignore
        if(productFromCart?.count!=undefined) {
            setTitleCart("Добавлен в корзину");
            setCountProductsInBag(productFromCart?.count);
        }else {
            setTitleCart("В корзину");
        }
        console.log("UseEffectProductFromCart?.count= "+productFromCart?.count)
    },[productDetail,productFromCart]);




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
                                                const newCount = prevCount - 1;
                                                if(productFromCart?.count==undefined) {
                                                    const productCard: IProductCart = {
                                                        // @ts-ignore
                                                        idProduct: productDetail?.id,
                                                        // @ts-ignore
                                                        accessToken: localStorage.getItem('token'),
                                                        count: newCount,
                                                        showInCart: true
                                                    }
                                                    CartController.addProductInCart(productCard);
                                                }
                                                //@ts-ignore
                                                sendCountProductInCart(productDetail?.id, newCount, localStorage.getItem('token'));
                                                // @ts-ignore
                                                // getProductFromCart(productDetail.id,localStorage.getItem('token'));
                                                return newCount;
                                            });
                                        }}></button>
                                        <input min="1" max="50" name="quantity" value={countProductsInBag} onChange={(e)=>setCountProductsInBag(parseInt(e.target.value,10))} type="number"/>
                                        <button className="ant107_shop-plus" onClick={()=>{
                                            setCountProductsInBag(prevCount => {
                                                const newCount = prevCount + 1;
                                                if(productFromCart?.count==undefined) {
                                                    const productCard: IProductCart = {
                                                        // @ts-ignore
                                                        idProduct: productDetail?.id,
                                                        // @ts-ignore
                                                        accessToken: localStorage.getItem('token'),
                                                        count: newCount,
                                                        showInCart: true
                                                    }
                                                    CartController.addProductInCart(productCard);
                                                }
                                                //@ts-ignore
                                                sendCountProductInCart(productDetail?.id, newCount, localStorage.getItem('token'));
                                                // // @ts-ignore
                                                // getProductFromCart(productDetail.id,localStorage.getItem('token'));

                                                return newCount;
                                            });
                                        }} ></button>
                                    </div>
                                    {//@ts-ignore
                                       productFromCart?.count==undefined  ?
                                        <div onClick={() => {
                                            const productCard: IProductCart = {
                                                // @ts-ignore
                                                idProduct: productDetail?.id,
                                                // @ts-ignore
                                                accessToken: localStorage.getItem('token'),
                                                count: countProductsInBag,
                                                showInCart: true
                                            }

                                            // setProductFromCart(cart);
                                            productCard.count=countProductsInBag||1;
                                            CartController.addProductInCart(productCard);
                                            // @ts-ignore
                                            localStorage.setItem('countProductInCart',parseInt(localStorage.getItem('countProductInCart'))+1);
                                            console.log("add "+productCard.count)
                                            setCountProductsInBag(productCard.count);
                                            setTitleCart("Добавлен в корзину");
                                            // @ts-ignore
                                            getProductFromCart(productDetail.id,localStorage.getItem('token'))
                                        }} className="ant107_shop-theme-btn ant107_shop-br-30 ml-3">{titleCart}</div> :

                                        <div className="ant107_shop-theme-btn ant107_shop-br-30 ml-3" onClick={() => {
                                            console.log("Добавлен в корзину")
                                            //@ts-ignore
                                            CartController.removeProductFromCart(productFromCart?.idProduct,localStorage.getItem('token'));
                                            // @ts-ignore
                                            localStorage.setItem('countProductInCart',parseInt(localStorage.getItem('countProductInCart'))-1);
                                            setCountProductsInBag(0);
                                            setProductFromCart(undefined);
                                            setTitleCart("Добавлен в корзину");
                                        }
                                        }>{titleCart}</div>
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