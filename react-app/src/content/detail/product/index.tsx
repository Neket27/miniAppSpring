import {useContext, useEffect, useState} from "react";
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
import {Message} from 'stompjs';
import {CardProductResponse} from "../../../model/response/product/CardProductResponse";
import {ContextService} from "../../../main";
import {ContextCountProductInBag} from "../../navbar";
import {IProductInBag} from "../../../model/bag/IProductInBag";
import ImageProduct from "./imageProduct";
import CartProduct from "./cartProduct";
import CartPreviewProduct from "./cartProduct";

const URL = import.meta.env.VITE_URL;

const DetailProduct = (props:any) => {
    const contextService = useContext(ContextService);
    const contextCountProductInCart = useContext(ContextCountProductInBag);
    const [accessToken,setAccessToken]= useState(localStorage.getItem("accessToken"));
    const location = useLocation();
    const { typeId } = useParams<{ typeId: string }>();
    const [productDetail, setProductDetail] = useState<IDetailProduct|undefined>(undefined);
    const [countProducts, setCountProducts] = useState<number>(0);

    const [showBlockDetail, setShowBlockDetail] = useState<boolean>(true);
    const [showBlockReview, setShowBlockReview] = useState<boolean>(false);
    const [productFromCart, setProductFromCart] = useState<ProductCartResponse | null>(null);
    const [titleCart, setTitleCart] = useState<string>('');
    const [relatedProducts, setRelatedProducts] = useState<CardProductResponse>();
    const [checkAddProduct, setCheckAddProduct] = useState<boolean>(false);

    const getNumberOfPiecesOfGoods =(val:Message)=>{
        console.log("полученно = "+val.body)
        setCountProducts(parseInt(val.body))
        contextService.productService.sendRequestOnGetCountProductInBag(accessToken);
    }

    const sendValue=(count:number)=>{
        contextService.productService.sendNumberOfPiecesOfGoods(productDetail?.id,count,accessToken);
        // contextService.productService.sendRequestOnGetCountProductInBag(accessToken);
    }

    async function getProductDetail(){
        if(typeId!=undefined) {
            const response = await contextService.productService.getProductDetail(parseInt(typeId, 10))
            setProductDetail(response);
        }else {
            console.log("ID продукта в getProductDetail == undefined")
        }
    }

    useEffect(() => { // useEffect выполняется при первой загрузке или перезагрузки страницы
        getProductDetail();

    }, []);

    useEffect(() => {
            contextService.productService.connect(
                getNumberOfPiecesOfGoods,
                accessToken,
                JSON.stringify({
                idProduct: productDetail?.id,
                accessToken: accessToken
            }));
            contextService.productService.sendRequestOnGetNumberOfPiecesOfGoods(productDetail?.id,accessToken);
            // contextService.productService.sendRequestOnGetCountProductInBag(accessToken);

    }, [productDetail,location]);


    useEffect(() => {

        // Функция очистки, которая будет выполнена при размонтировании компонента
        return () => {
            console.log('Компонент размонтирован, выполняется cleanup');
            if(countProducts>0 && productDetail?.id!=undefined && accessToken!=null){
            const product:IProductInBag= {
                idProduct: productDetail.id,
                count: countProducts,
                showInCart: true,
                accessToken:accessToken
            }
                contextService.bagService.addProductInCart(product);
            }

        };
    }, [productDetail]);


    useEffect(()=>{
        if(countProducts>0)
            setCheckAddProduct(true);
        console.log("setCheckAddProduct(true);")

        return  sendValue(countProducts);
    });


    const handleClickMinus = () => {
        if(checkAddProduct && countProducts==1){
            let countStr = localStorage.getItem("countProductInBag");
            console.log("countStr= "+countStr);
            if (countStr != null) {
                const count = parseInt(countStr) -1;
                localStorage.setItem("countProductInBag",String(count));
                contextCountProductInCart.setCountProductInBag(count);
            }

                setCheckAddProduct(false);
        }

        const count =Math.max(countProducts-1,0);
        setCountProducts(count);
        sendValue(count);
    };

    const handleClickPlus = () => {
        if(!checkAddProduct && countProducts==0){
            let countStr = localStorage.getItem("countProductInBag");
            console.log("countStr= "+countStr)
            if (countStr != null) {
                const count = parseInt(countStr) + 1;
                localStorage.setItem("countProductInBag", String(count));
                contextCountProductInCart.setCountProductInBag(count);
            }
            setCheckAddProduct(true);
        }

        const count =countProducts+1;
        setCountProducts(count);
        sendValue(count);
    };

    const imagesMain =(<ImageProduct base64={productDetail?.imageDtoList.at(0)?.base64}/>);

    const images =productDetail?.imageDtoList.map((image,index) =>
        <ImageProduct key={index} base64={image.base64}/>
    );

    const relatedProductListJsx = relatedProducts?.cardsProduct.map((product,) =>{
        return <CartPreviewProduct key={product.id} product={product}/>
    });


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
                                        <img
                                            src={URL + "/api/v1/home/get-image-with-media-type?id=" + productDetail?.id}
                                            alt=""
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
                                        <button className="ant107_shop-minus" onClick={() => {
                                            handleClickMinus()
                                        }}></button>
                                        <input min="1" max="50" name="quantity" value={countProducts}
                                               type="number"  onChange={()=>{}} />
                                        <button className="ant107_shop-plus" onClick={() => {
                                            handleClickPlus()
                                        }}></button>
                                    </div>
                                    {
                                        !checkAddProduct ?
                                            <div onClick={() => {
                                                // {contextService.productService.sendNumberOfPiecesOfGoods(productDetail?.id,1,accessToken);}}
                                                let countStr = localStorage.getItem("countProductInBag");
                                                console.log("countStr= "+countStr);
                                                if (countStr != null) {
                                                    const count = parseInt(countStr) +1;
                                                    localStorage.setItem("countProductInBag",String(count));
                                                    contextCountProductInCart.setCountProductInBag(count);
                                                    setCountProducts(1);
                                                    setCheckAddProduct(true);
                                                }
                                            }
                                            }
                                                 className="ant107_shop-theme-btn ant107_shop-br-30 ml-3">В
                                                корзину
                                            </div> :
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
