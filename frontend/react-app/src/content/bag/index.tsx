import { useContext, useEffect, useState, useCallback } from "react";
import { ProductCartResponse } from "../../model/response/product/ProductCartResponse";
import { Link } from "react-router-dom";
import { IProductBag } from "../../model/product/IProductBag";
import {ContextService, locationDetector} from "../../main";
import CartProductInBag from "./CartProductInBag";
import { ICoupon } from "../../model/coupon/ICoupon";
import { ContextCountProductInBag } from "../navbar";
import OpenAI from "openai";

const Bag = () => {
    const contextService = useContext(ContextService);
    const contextCountProductInBag = useContext(ContextCountProductInBag);
    const accessToken = localStorage.getItem("accessToken");

    const [products, setProducts] = useState<IProductBag[]>([]);
    const [inputValues, setInputValues] = useState<Record<number, number>>({});
    const [subtotal, setSubtotal] = useState(0);
    const [totalPrice, setTotalPrice] = useState<number>(0);
    const [amountDelivery, setAmountDelivery] = useState(0);
    const [amountDiscount, setAmountDiscount] = useState(0);
    const [amountCoupon, setAmountCoupon] = useState(0);
    const [couponTitle, setCouponTitle] = useState<string>('');
    const [responseCheckCoupon, setResponseCheckCoupon] = useState<ICoupon | null>(null);
    const [textForInvalidCoupon, setTextForInvalidCoupon] = useState<string>('');


    const getDataPay = async() => {
        const payData = await contextService.payService.getDataPay(localStorage.getItem("city"));
        setSubtotal(payData.amountPay);
        setAmountDelivery(payData.amountDeliver);
        setAmountDiscount(payData.amountDiscount);
        console.log(payData.amountDiscount);
        setAmountCoupon(payData.amountCoupon);
        setTotalPrice(payData.finalAmount);
    }


    const getProductsFromCart = useCallback(async () => {
        if (accessToken) {
            try {
                const result: ProductCartResponse = await contextService.bagService.getProductsFromCart(accessToken);
                setProducts(result.productsInCard);
                //contextService.bagService.sendRequestOnGetCountProductInBag(accessToken); закоментил
            } catch (error) {
                console.error("Error fetching products:", error);
            }
        }
    }, [accessToken, contextService.bagService]);



    useEffect(() => {
        getProductsFromCart();
            getDataPay()
    }, [getProductsFromCart]);

    const updateTotalPrice = useCallback(() => {
        const newTotalPrice = products.reduce((acc, product) => acc + product.cost * product.count, 0);
        const finalPrice = responseCheckCoupon ? newTotalPrice - responseCheckCoupon.amount : newTotalPrice;
        setTotalPrice(Math.max(finalPrice, 0)); // Не допускаем отрицательных значений
    }, [products, responseCheckCoupon]);

    useEffect(() => {
        updateTotalPrice();
    }, [products, updateTotalPrice]);

    const handleProductCountChange = async (id: number, newCount: number) => {
        const updatedProducts = products.map((product) =>
            product.idProduct === id ? { ...product, count: newCount } : product
        );
        setProducts(updatedProducts);

        if (accessToken) {
            await contextService.bagService.sendCountProductInCart(id, newCount, accessToken);
        }
    };

    const handleIncrement = (id: number) => {
        const newCount = (inputValues[id] || 0) + 1;
        setInputValues((prevState) => ({ ...prevState, [id]: newCount }));
        handleProductCountChange(id, newCount);
    };

    const handleDecrement = (id: number) => {
        const newCount = Math.max((inputValues[id] || 0) - 1, 0);
        setInputValues((prevState) => ({ ...prevState, [id]: newCount }));
        handleProductCountChange(id, newCount);
    };

    const checkCoupon = async (coupon: string) => {
        const response = await contextService.couponService.checkCoupon(coupon);

        if (response) {
            if (!responseCheckCoupon || response.amount > responseCheckCoupon.amount) {
                setResponseCheckCoupon(response);
                setAmountCoupon(response.amount);
            }
            setTextForInvalidCoupon('Купон применён');
        } else {
            setTextForInvalidCoupon('Купон не найден');
        }

        setTimeout(() => setTextForInvalidCoupon(''), 2000);
    };

    const removeProductFromCart = async (idProduct: number) => {
        if (accessToken) {
            const response: ProductCartResponse = await contextService.bagService.removeProductFromCart(idProduct, accessToken);
            setProducts(response.productsInCard);
            contextCountProductInBag.setCountProductInBag((prev) => prev - 1);
        }
    };

    const productsJsx = products.map((product) => {
        inputValues[product.idProduct] = product.count;
        return <CartProductInBag
            key={product.idProduct}
            product={product}
            accessToken={accessToken}
            removeProductFromCart={removeProductFromCart}
            inputValues={inputValues}
            handleInputChange={(id, value) => setInputValues((prev) => ({...prev, [id]: parseInt(value)}))}
            handleIncrement={handleIncrement}
            handleDecrement={handleDecrement}
        />
    });

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
                                        <form>
                                            <p>{textForInvalidCoupon}</p>
                                            <input
                                                className="ant107_shop-br-10"
                                                type="text"
                                                placeholder="Купон"
                                                value={couponTitle}
                                                onChange={(e) => setCouponTitle(e.target.value)}
                                            />
                                            <button
                                                type="button"
                                                className="ant107_shop-theme-btn ant107_shop-no-shadow ant107_shop-bg-black ant107_shop-br-10"
                                                onClick={() => checkCoupon(couponTitle)}
                                            >
                                                Применить купон
                                            </button>
                                        </form>
                                    </div>
                                </div>
                                <div className="col-lg-7 text-lg-right">
                                    <Link to="/" className="ant107_shop-theme-btn ant107_shop-br-10">
                                        Вернуться в магазин
                                    </Link>
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
                                            <span className="ant107_shop-price">{subtotal}</span>
                                        </div>
                                        <div className="ant107_shop-total-item ant107_shop-shipping">
                                            <span className="ant107_shop-title">Доставка</span>
                                            <span className="ant107_shop-price">{amountDelivery==0?'Бесплатно':amountDelivery}</span>
                                        </div>
                                            {amountDiscount != 0 ?
                                                <div className="ant107_shop-total-item ant107_shop-discount">
                                                    <span className="ant107_shop-title">Скидка</span>
                                                    <span className="ant107_shop-price">{amountDiscount}</span>
                                                </div>:''
                                            }

                                        {responseCheckCoupon != null || amountCoupon>0 ?
                                            <div className="ant107_shop-total-item ant107_shop-discount">
                                                <span className="ant107_shop-title">Купон</span>
                                                <span
                                                    className="ant107_shop-price">{amountCoupon}</span>
                                            </div>:''
                                        }
                                        <div className="ant107_shop-total-item ant107_shop-total">
                                            <span className="ant107_shop-title">Итого</span>
                                            <span className="ant107_shop-price">{totalPrice-amountDiscount+amountDelivery-amountCoupon}</span>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-xl-7 col-lg-6 text-right">
                                    <div className="ant107_shop-proceed-btn mt-4">
                                        <Link to="/pay" className="ant107_shop-theme-btn ant107_shop-br-10">
                                        К оплате
                                        </Link>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </main>
                </div>
            </div>
        </div>
    );
};

export default Bag;
