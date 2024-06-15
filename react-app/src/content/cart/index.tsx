import {useEffect, useState} from "react";
import {ProductCartResponse} from "../../product/model/response/ProductCartResponse";
import CartController from "./controller/CartController";
import {Link} from "react-router-dom";

import {Client, Frame, over} from 'stompjs';
import SockJS from 'sockjs-client';
const URL = import.meta.env.VITE_URL;

let stompClient:Client;

const Cart =()=>{

    const [products, setProducts] = useState<Array<ProductCartResponse>>([])
    const [inputValues, setInputValues] = useState<Record<number, number>>({});
    const [totalPrice, setTotalPrice] = useState<number>(0);
    const token:string|null =localStorage.getItem('token');

    const connect =()=>{
        let Sock = new SockJS('http://localhost:8080/ws');
        stompClient = over(Sock);
        stompClient.connect({},(frame) => {onConnected();}, (error:Frame|string) => {onError(error);});
    }

    const onConnected = () => {
        // stompClient.subscribe('/shoppingCart/public', onCountReceived);
        stompClient.subscribe('/shoppingCartCountProduct/public');
        sendCountProductInCart2();
    }


    const sendCountProductInCart2 =()=>{
        if (token!=null)
            stompClient.send("/app/getCountProductInCart", {},token);
        else
            console.log("Токен для sendCountProductInCart2 = "+token)
    }


    const onError = (err: Frame | string) => {
        console.log(err);
    }


    async function removeProductFromCart(idProduct:number, accessToken:string) {
        const response = await CartController.removeProductFromCart(idProduct, accessToken)
        setProducts(response);
    }
    async function getProductsFromCart(accessToken:string){
        try {
            const result = await CartController.getProductsFromCart(accessToken);
            setProducts(result);
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

    useEffect(()=>{
        if (token!=null)
            getProductsFromCart(token);
        connect();

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
        if(token!=null) {
            sendCountProductInCart(id, updatedCount, token);
            updateTotalPrice(id, updatedCount);
        }else
            console.log("Токен для handleIncrement = "+token);
    };

    const handleDecrement = (id:number) => {
        setInputValues(prevState => ({
            ...prevState,
            [id]: Math.max((prevState[id] || 0) - 1, 0),
        }));
        const updatedCount = Math.max(inputValues[id] - 1, 0);
        if(token!=null) {
        sendCountProductInCart(id, updatedCount,token);
        updateTotalPrice(id, updatedCount);
        }else
            console.log("Токен для handleIncrement = "+token);
    };

    const updateTotalPrice = (id: number, count: number) => {
        const product = products.find(product => product.idProduct === id);
        if (product) {
            const productIndex = products.indexOf(product);
            const updatedProducts = [...products];
            updatedProducts[productIndex] = { ...product, count };
            setProducts(updatedProducts);

            const totalPrice = updatedProducts.reduce((acc, curr) => acc + (curr.cost * curr.count), 0);
            setTotalPrice(totalPrice);
        }
    };


    useEffect(() => {
        // products - это состояние, содержащее массив продуктов
        products.forEach((product) => {
            setInputValues((prevInputValues) => ({
                ...prevInputValues,
                [product.idProduct]: product.count
            }));
        });

        const totalPrice = products.reduce((acc, curr) => acc + (curr.cost * curr.count), 0);
        setTotalPrice(totalPrice);
    }, [products]);


    const productsJsx = products.map((product,index) =>
        <div key={index} className="ant107_shop-cart-single-item">
            <button type="button" className="close" onClick={() => {
                //@ts-ignore
                removeProductFromCart(product.idProduct, localStorage.getItem('token'));
                // // @ts-ignore
                // localStorage.setItem(localStorage.getItem('countProductInCart')-1);
                // @ts-ignore
                getProductsFromCart(localStorage.getItem('token'));
            }}><i className="fas fa-times"></i>
            </button><button onClick={sendCountProductInCart2}>bbb</button>
            <div className="ant107_shop-product-img">
                <img src={"data:image/png;base64,"+product.imageDtoList.at(0)?.base64} alt=""   style={{ width: "100px", height: "100px" }} />
            </div>
            <h5 className="ant107_shop-product-name">{product.name}</h5>
            <h5 className="ant107_shop-product-price">{product.cost}</h5>
            <div className="ant107_shop-number-input">
                <button className="ant107_shop-minus" onClick={() => handleDecrement(product.idProduct)}></button>
                <input key={product.idProduct} className="quantity" min="1" name="quantity"
                       value={inputValues[product.idProduct] || 0}
                       onChange={(e) => handleInputChange(product.idProduct, e.target.value)}
                       type="number"/>
                <button className="ant107_shop-plus" onClick={() => handleIncrement(product.idProduct)}></button>
            </div>
            <h5 className="ant107_shop-product-total-price">{product.cost * product.count}</h5>
        </div>
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
                                                <button
                                                    className="ant107_shop-theme-btn ant107_shop-no-shadow ant107_shop-bg-black ant107_shop-br-10"
                                                    type="submit">Применить купон
                                                </button>
                                            showInCart:true</form>
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

export default Cart;