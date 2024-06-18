import {useEffect, useState} from "react";
import '../../../init';
import {Link, useLocation, useParams} from "react-router-dom";
import {IDetailProduct} from "../../../model/product/IDetailProduct";
import "./../../../../css/magnify.css";
import "./../../../../css/ant107_shop.css";
import "./../../../../js/jquery.magnify.js"
import Review from "./swithBlocks/review";
import Detail from "./swithBlocks/detail";
import {ProductCartResponse} from "../../../model/response/product/ProductCartResponse";
import {observer} from "mobx-react-lite";
import {Client, Frame, Message, over} from 'stompjs';
import SockJS from 'sockjs-client';
import {CardProductResponse} from "../../../model/response/product/CardProductResponse";
import ProductService from "../../../service/product/ProductService";

const URL = import.meta.env.VITE_URL;

let stompClient:Client;

const DetailProduct = (props:any) => {
    const location = useLocation();
    const { typeId } = useParams<{ typeId: string }>();
    const [productDetail, setProductDetail] = useState<IDetailProduct|undefined>(undefined);
    const [countProductsInCart, setCountProductsInCart] = useState<number>(0);

    const [showBlockDetail, setShowBlockDetail] = useState<boolean>(true);
    const [showBlockReview, setShowBlockReview] = useState<boolean>(false);
    const [productFromCart, setProductFromCart] = useState<ProductCartResponse | null>(null);
    const [titleCart, setTitleCart] = useState<string>('');
    const [relatedProducts, setRelatedProducts] = useState<CardProductResponse>();
    const accessToken:string|null =localStorage.getItem('accessToken');


    const connect =()=>{
        let Sock = new SockJS('http://localhost:8080/ws');
        stompClient = over(Sock);
        stompClient.connect(
            {},
            (frame) => {onConnected();},
            (error:Frame|string) => {onError(error);}
        );
    }

    const onConnected = () => {
        stompClient.subscribe('/shoppingCart/public', getNumberOfPiecesOfGoods);
        stompClient.subscribe('/shoppingCartCountProduct/public', getShoppingCartCountProduct);
        sendNumberOfPiecesOfGoods();
    }

    const sendNumberOfPiecesOfGoods =()=>{
        stompClient.send("/app/getNumberOfPiecesOfGoods", {},JSON.stringify({idProduct:productDetail?.id,accessToken:accessToken}));
    }

    const sendCountProductsInCart =()=>{
        if(accessToken!=null)
            stompClient.send("/app/getCountProductInCart", {},accessToken);
        else
            onError("Токен в sendCountProductInCart == null");
    }

    const getNumberOfPiecesOfGoods =(val:Message)=>{
        setCountProductsInCart(parseInt(val.body))
        sendCountProductsInCart();
    }

    const getShoppingCartCountProduct =(val:Message)=>{
      //  console.log("ShoppingCartCountProduct= "+val.body)
    }

    const onError = (err:Frame|string) => {
        console.log("err= "+err);
    }

    const sendValue=(count:number)=>{
        if (stompClient) {
            stompClient.send("/app/sendNumberOfPiecesOfGoods", {}, JSON.stringify({
                idProduct: productDetail?.id,
                count: count,
                accessToken: accessToken
            }))

        }else{
            console.log("Стом клиент не создан")
        }
    }

    async function getProductDetail(){
        if(typeId!=undefined) {
            const response = await ProductService.getProductDetail(parseInt(typeId, 10))
            setProductDetail(response);
        }else {
            console.log("ID продукта в getProductDetail == undefined")
        }
    }

    useEffect(() => { // useEffect выполняется при первой загрузке или перезагрузки страницы
        getProductDetail();

    }, []);

    useEffect(() => {
        connect();
    }, [productDetail]);


    const handleClickMinus = () => {
        const count =Math.max(countProductsInCart-1,0);
        setCountProductsInCart(count);
        sendValue(count);
    };

    const handleClickPlus = () => {
        const count =countProductsInCart+1;
        setCountProductsInCart(count);
        sendValue(count);
    };

    const imagesMain =(
        <div className="tab-pane active" id="ant107_shop-preview2">
            <img src={"data:image/png;base64," +productDetail?.imageDtoList.at(0)?.base64 } alt=""
                 data-magnify-src={"data:image/png;base64," +productDetail?.imageDtoList.at(0)?.base64}/>
        </div>
    );

    const images =productDetail?.imageDtoList.map(image =>
        <li>
            <a data-toggle="tab" href="#ant107_shop-preview1">
            <img src={"data:image/png;base64,"+image.base64} alt=""/>
            </a>
        </li>
    );

    const relatedProductListJsx = relatedProducts?.cardsProduct.map(product =>{
        return <div key={product.id} className="col-xl-3 col-lg-4 col-sm-6">
            <div className="ant107_shop-shop-box">
                <div className="ant107_shop-shop-img">
                    <a href="#!">
                    <img src={"data:image/png;base64,"+product.imageDtoList.at(0)?.base64} alt=""/>
                    </a>
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

    return (
        <div id="ant107_shop" className="ant107_shop_container">
            <div className="container">
                <main>
                    <div className="row">
                        <div className="col-lg-6">
                            <div className="ant107_shop-product-preview-wrap">
                                <div className="tab-content">
                                    <div className="tab-pane" id="ant107_shop-preview1">
                                        <img src={URL + "/api/v1/home/get-image-with-media-type?id=" + productDetail?.id} alt=""
                                            data-magnify-src={URL + "/api/v1/home/get-image-with-media-type?id=" + productDetail?.id}/>
                                    </div>
                                    {imagesMain}
                                    <div className="tab-pane active" id="ant107_shop-preview2">
                                        {/*<img*/}
                                        {/*    src={"data:image/png;base64," + productDetail?.imageDtoList.at(0)?.base64}*/}
                                        {/*    alt=""*/}
                                        {/*    data-magnify-src={"data:image/png;base64," + productDetail?.imageDtoList.at(0)?.base64}/>*/}
                                    </div>

                                </div>
                                <ul className="nav nav-tabs d-flex align-content-between">{images}</ul>
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
                                <h6>Участвует в акции: <span>{productDetail?.available ? "Да" : "Нет"}</span></h6>

                                <div className="ant107_shop-product-spinner mt-3">
                                    <div className="ant107_shop-number-input">
                                        <button className="ant107_shop-minus" onClick={() => {handleClickMinus()}}></button>
                                        <input min="1" max="50" name="quantity" value={countProductsInCart}
                                               type="number"/>
                                        <button className="ant107_shop-plus" onClick={() => {handleClickPlus()}}></button>
                                    </div>
                                    {
                                        countProductsInCart==0 ?
                                            <div onClick={()=>{
                                                if (stompClient) {
                                                    stompClient.send("/app/sendNumberOfPiecesOfGoods", {}, JSON.stringify({
                                                        idProduct: productDetail?.id,
                                                        count: 1,
                                                        accessToken: accessToken
                                                    }))

                                                }else{
                                                    console.log("Стом клиент не создан")
                                                }
                                            }
                                        }
                                                 className="ant107_shop-theme-btn ant107_shop-br-30 ml-3">В корзину</div> :

                                            <Link to="/cart">
                                                <div className="ant107_shop-theme-btn ant107_shop-br-30 ml-3">
                                                    Добавлен в корзину
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


export default observer(DetailProduct);
