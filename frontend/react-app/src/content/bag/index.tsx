import {useContext, useEffect, useState} from "react";
import {ProductCartResponse} from "../../model/response/product/ProductCartResponse";
import {Link} from "react-router-dom";
import {IProductBag} from "../../model/product/IProductBag";
import {ContextService} from "../../main";
import bagService from "../../service/bag/BagService";
import {ContextCountProductInBag} from "../navbar";
import CartProductInBag from "./CartProductInBag";

const Bag =()=>{
    const contextService = useContext(ContextService);
    const contextCountProductInBag = useContext(ContextCountProductInBag);
    const accessToken:string|null =localStorage.getItem("accessToken");
    const [products, setProducts] = useState<Array<IProductBag>>()
    const [inputValues, setInputValues] = useState<Record<number, number>>({});
    const [totalPrice, setTotalPrice] = useState<number>(0);

    async function removeProductFromCart(idProduct:number, accessToken:string) {
        const response:ProductCartResponse = await contextService.bagService.removeProductFromCart(idProduct, accessToken)
        setProducts(response.productsInCard);

        const count = localStorage.getItem("countProductInBag");
        if(count!=null)
            contextCountProductInBag.setCountProductInBag(parseInt(count)-1);
    }

    async function getProductsFromCart(accessToken:string){
        try {
            const result:ProductCartResponse = await contextService.bagService.getProductsFromCart(accessToken);
            setProducts(result.productsInCard);
            contextService.bagService.sendRequestOnGetCountProductInBag(accessToken);
        } catch (error) {
            console.error("Error:", error);
        }
    }

    const sendCountProductInCart = async (idProduct: number, count: number, accessToken: string) => {
        if (!isNaN(count)) {  // Проверка на то, что count является числом
            const response = await contextService.bagService.sendCountProductInCart(idProduct, count, accessToken);
        } else {
            console.error("Ошибка: Невалидное значение countProductsInBag");
        }
    };

    useEffect(()=>{
        if (accessToken!=null)
            getProductsFromCart(accessToken);


    },[]);

    const handleInputChange = (id:number, value:string) => {
        setInputValues(prevState => ({ ...prevState, [id]: parseInt(value) }));
    };

    const handleIncrement = (id:number) => {
        setInputValues(prevState => ({
            ...prevState,
            [id]: (prevState[id] || 0) + 1,
        }));
        const updatedCount = inputValues[id] + 1;
        if(accessToken!=null) {
            sendCountProductInCart(id, updatedCount, accessToken);
            updateTotalPrice(id, updatedCount);
        }else
            console.log("Токен для handleIncrement = "+accessToken);
    };

    const handleDecrement = (id:number) => {
        setInputValues(prevState => ({
            ...prevState,
            [id]: Math.max((prevState[id] || 0) - 1, 0),
        }));
        const updatedCount = Math.max(inputValues[id] - 1, 0);
        if(accessToken!=null) {
        sendCountProductInCart(id, updatedCount,accessToken);
        updateTotalPrice(id, updatedCount);
        }else
            console.log("Токен для handleIncrement = "+accessToken);
    };

    const updateTotalPrice = (id: number, count: number) => {
        const product:IProductBag|undefined = products?.find(product => product.idProduct === id);
        if (product) {
            const productIndex:number|undefined = products?.indexOf(product);
            // @ts-ignore
            const updatedProducts:Array<IProductBag> = [...products];
            if(productIndex!=undefined)
                updatedProducts[productIndex] = { ...product, count };
            setProducts(updatedProducts);

            const totalPrice = updatedProducts.reduce((acc, curr) => acc + (curr.cost * curr.count), 0);
            setTotalPrice(totalPrice);
        }
    };


    useEffect(() => {
        // products - это состояние, содержащее массив продуктов
        products?.forEach((product) => {
            setInputValues((prevInputValues) => ({
                ...prevInputValues,
                [product.idProduct]: product.count
            }));
        });


        const totalPrice:number|undefined = products?.reduce((acc, curr) => acc + (curr.cost * curr.count), 0);
        if(totalPrice!=undefined)
            setTotalPrice(totalPrice);
    }, [products]);


    const productsJsx = products?.map((product, index) =>
       <CartProductInBag key={index} product={product} accessToken={accessToken} removeProductFromCart={removeProductFromCart} inputValues={inputValues} handleInputChange={handleInputChange} handleIncrement={handleIncrement} handleDecrement={handleDecrement}></CartProductInBag>
    );

    return (
        <div id="ant107_shop" className="ant107_shop_container">
            <div className="container">
                <div className="row">

                    <main className="col">
                        <div className="ant107_shop-cart-total-product mb-5">
                            <div className="ant107_shop-cart-title d-none d-md-flex">
                                <h3 className="ant107_shop-product-title">Товар</h3>
                                <h3 className="ant107_shop-price-title">Цена</h3>
                                <h3 className="ant107_shop-quantity-title">Кол-во</h3>
                                <h3 className="ant107_shop-total-title mr-4">Итого</h3>
                            </div>
                            <div className="ant107_shop-cart-items pb-3">
                                {productsJsx}

                                </div>

                                <div className="row text-center text-lg-left">
                                    <div className="col-lg-5">
                                        <div className="ant107_shop-discount-wrapper">
                                            <form action="#" className="d-lg-block">
                                                <input className="ant107_shop-br-10" type="text" placeholder="Купон"/>
                                                <button className="ant107_shop-theme-btn ant107_shop-no-shadow ant107_shop-bg-black ant107_shop-br-10"
                                                    type="submit">Применить купон
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                    <div className="col-lg-7">
                                        <div className="ant107_shop-update-shopping text-lg-right">
                                            <a href="#"
                                               className="ant107_shop-theme-btn ant107_shop-no-shadow ant107_shop-style-two ant107_shop-br-10 mr-3">Обновить
                                                корзину</a>
                                            <Link to="/" className="ant107_shop-theme-btn ant107_shop-br-10">Вернуться в магазин</Link>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="ant107_shop-cart-total-price">
                                <div className="ant107_shop-cart-title">
                                    <h3>Итого</h3>
                                </div>
                                <div className="row">
                                    <div className="col-xl-5 col-lg-6">
                                        <div className="ant107_shop-total-item-wrap">
                                            <div className="ant107_shop-total-item ant107_shop-sub-total">
                                                <span className="ant107_shop-title">Подытог</span>
                                                <span className="ant107_shop-price">{totalPrice}</span>
                                            </div>
                                            <div className="ant107_shop-total-item ant107_shop-shipping">
                                                <span className="ant107_shop-title">Доставка</span>
                                                <span className="ant107_shop-price">100</span>
                                            </div>
                                            <div className="ant107_shop-total-item ant107_shop-discount">
                                                <span className="ant107_shop-title">Скидка</span>
                                                <span className="ant107_shop-price">20</span>
                                            </div>
                                            <div className="ant107_shop-total-item ant107_shop-total">
                                                <span className="ant107_shop-title mb-0">Итого</span>
                                                <span className="ant107_shop-price mb-0">{totalPrice}</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-xl-7 col-lg-6 text-right align-self-end">
                                        <div className="ant107_shop-proceed-btn mt-4">
                                            <a href="checkout.html" className="ant107_shop-theme-btn ant107_shop-br-10">К
                                                оплате</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </main>

                    </div>
                </div>
            </div>
    );
}

export default Bag;