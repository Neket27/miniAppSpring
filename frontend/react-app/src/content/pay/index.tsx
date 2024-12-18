import {useContext, useEffect, useState} from "react";
import {ContextService, locationDetector} from "../../main";
import {IProductBag} from "../../model/product/IProductBag";

export const PagePay = ()=>{

    const contextService = useContext(ContextService);
    const accessToken = localStorage.getItem("accessToken");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [organization, setOrganization] = useState("");
    const [streetAddress, setStreetAddress] = useState("");
    const [city, setCity] = useState("");
    const [cityWritingUser, setCityWritingUser] = useState("");
    const [email, setEmail] = useState("");
    const [products, setProducts] = useState<IProductBag[]>([]);

    const [subtotal, setSubtotal] = useState(0);
    const [amountDelivery, setAmountDelivery] = useState('');
    const [amountDiscount, setAmountDiscount] = useState(0);
    const [totalAmount, setTotalAmount] = useState(0);
    const [amountCoupon, setAmountCoupon] = useState(0);

    const getAndSetTown = async()=>{
        setCity(localStorage.getItem("city"));
    }

    const getDataPay = async(city:string) => {
           const payData = await contextService.payService.getDataPay(city);
           setSubtotal(payData.amountPay);
           setAmountDelivery(payData.amountDeliver==0?'Бесплатно':payData.amountDeliver);
           setAmountDiscount(payData.amountDiscount);
           setAmountCoupon(payData.amountCoupon);
           setTotalAmount(payData.finalAmount);
    }

    useEffect(() => {
        setFirstName(contextService.authService.user.firstname);
        setLastName(contextService.authService.user.lastname);
        setOrganization('')
        getAndSetTown();
        setEmail(contextService.authService.user.email);
        if(city!='') {
            getDataPay(city);
            setCityWritingUser(city);
        }
    }, [city]);

    useEffect(() => {
        //getDataPay(cityWritingUser);
    }, [cityWritingUser]);

    return(
    <div id="ant107_shop" class="ant107_shop_container">
        <div class="container">
            <div class="row">

                <main class="col-xl-7 col-lg-6">
                    <div class="ant107_shop-checkout-form-wrap">
                        <div class="ant107_shop-cart-title">
                            <h3>Детали оплаты</h3>
                        </div>
                        <form id="ant107_shop-checkout-form" class="ant107_shop-checkout-form" action="#" method="POST">
                            <div className="row clearfix">
                                <div className="col-md-6">
                                    <div className="form-group">
                                        <input type="text"
                                               className="form-control"
                                               placeholder="Имя"
                                               value={firstName}
                                               onChange={(e) => setFirstName(e.target.value)}
                                               required/>
                                    </div>
                                </div>
                                <div className="col-md-6">
                                    <div className="form-group">
                                        <input type="text"
                                               className="form-control"
                                               placeholder="Фамилия"
                                               value={lastName}
                                               onChange={(e) => setLastName(e.target.value)}
                                               required/>
                                    </div>
                                </div>
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <input type="text"
                                               className="form-control"
                                               value={organization}
                                               onChange={(e) => setOrganization(e.target.value)}
                                               placeholder="Организация"/>
                                    </div>
                                </div>
                                {/*<div class="col-md-12">*/}
                                {/*    <div class="form-group">*/}
                                {/*        <select class="form-control" name="select1">*/}
                                {/*            <option value="value1">Страна</option>*/}
                                {/*            <option value="value2">Россия</option>*/}
                                {/*            <option value="value3">Германия</option>*/}
                                {/*            <option value="value4">США</option>*/}
                                {/*            <option value="value5">Турция</option>*/}
                                {/*        </select>*/}
                                {/*    </div>*/}
                                {/*</div>*/}

                                <div className="col-md-12">
                                    <div className="form-group">
                                        <input type="text"
                                               className="form-control"
                                               placeholder="Город"
                                               value={cityWritingUser}
                                               onChange={(e) => {
                                                   setCityWritingUser(e.target.value)
                                                   getDataPay(e.target.value)
                                               }
                                        }
                                               required/>
                                    </div>
                                </div>

                                <div className="col-md-12">
                                    <div className="form-group">
                                        <input type="text"
                                               className="form-control"
                                               placeholder="Улица"
                                               value={streetAddress}
                                               onChange={(e) => setStreetAddress(e.target.value)}
                                               required/>
                                    </div>
                                </div>

                                {/*<div className="col-md-12">*/}
                                {/*    <div className="form-group">*/}
                                {/*        <select class="form-control" name="select2">*/}
                                {/*            <option value="value1">Район</option>*/}
                                {/*            <option value="value2">Центральный</option>*/}
                                {/*            <option value="value3">Восточный</option>*/}
                                {/*            <option value="value4">Западный</option>*/}
                                {/*            <option value="value5">Северный</option>*/}
                                {/*        </select>*/}
                                {/*    </div>*/}
                                {/*</div>*/}
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <input type="text"
                                               className="form-control"
                                               placeholder="Почтовый индекс"
                                               required/>
                                    </div>
                                </div>
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <input type="text"
                                               className="form-control"
                                               placeholder="Номер телефона"/>
                                    </div>
                                </div>
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <input type="email"
                                               className="form-control"
                                               placeholder="Email"
                                               value={email}
                                               onChange={(e) => setEmail(e.target.value)}
                                               required/>
                                    </div>
                                </div>
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <textarea class="form-control mb-0" placeholder="Комментарий" rows="8"
                                                  required></textarea>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </main>

                <aside class="col-xl-5 col-lg-6">
                    <div class="ant107_shop-checkout-cart-total clearfix">
                        <div class="ant107_shop-cart-title">
                            <h3>Итоги покупки</h3>
                        </div>

                        <div class="ant107_shop-total-item-wrap clearfix mb-4">
                            <div class="ant107_shop-total-item ant107_shop-sub-total">
                                <span class="ant107_shop-title">Подытог</span>
                                <span class="ant107_shop-price">{subtotal}</span>
                            </div>
                            <div class="ant107_shop-total-item ant107_shop-shipping">
                                <span class="ant107_shop-title">Доставка</span>
                                    <span className="ant107_shop-price">{amountDelivery}</span>
                            </div>
                            {amountDiscount != 0 ? <div className="ant107_shop-total-item ant107_shop-discount">
                                <span className="ant107_shop-title">Скидка</span>
                                <span className="ant107_shop-price">{amountDiscount}</span>
                            </div>:''
                            }
                            {amountCoupon != 0 ? <div className="ant107_shop-total-item ant107_shop-discount">
                                <span className="ant107_shop-title">Купон</span>
                                <span className="ant107_shop-price">{amountCoupon}</span>
                            </div>:''
                            }
                            <div class="ant107_shop-total-item ant107_shop-total">
                                <span class="ant107_shop-title mb-0">Итого</span>
                                <span class="ant107_shop-price mb-0">{totalAmount}</span>
                            </div>
                        </div>

                {/*        <ul id="ant107_shop-accordion" class="mb-4">*/}
                {/*            <li class="custom-control custom-radio">*/}
                {/*                <input type="radio" class="custom-control-input" id="ant107_shop-methodone" name="ant107_shop-radioname" checked>*/}
                {/*                    <label class="custom-control-label" for="ant107_shop-methodone" data-toggle="collapse" data-target="#ant107_shop-collapseOne" aria-controls="ant107_shop-collapseOne">Оплата картой</label>*/}

                {/*                    <div id="ant107_shop-collapseOne" class="collapse show" data-parent="#ant107_shop-accordion">*/}
                {/*                        <p>Перераспределение бюджета, безусловно, обуславливает институциональный социальный статус.</p>*/}
                {/*                    </div>*/}
                {/*            </li>*/}

                {/*            <li class="custom-control custom-radio">*/}
                {/*                <input type="radio" class="custom-control-input" id="ant107_shop-methodtwo" name="ant107_shop-radioname"/>*/}
                {/*                    <label class="custom-control-label collapsed" for="ant107_shop-methodtwo" data-toggle="collapse" data-target="#ant107_shop-collapseTwo" aria-controls="ant107_shop-collapseTwo">Оплата наличными</label>*/}

                {/*                    <div id="ant107_shop-collapseTwo" class="collapse" data-parent="#ant107_shop-accordion">*/}
                {/*                        <p>Перераспределение бюджета, безусловно, обуславливает институциональный социальный статус.</p>*/}
                {/*                    </div>*/}
                {/*            </li>*/}

                {/*            <li class="custom-control custom-radio">*/}
                {/*                <input type="radio" class="custom-control-input" id="ant107_shop-methodthree" name="ant107_shop-radioname"/>*/}
                {/*                    <label class="custom-control-label collapsed" for="ant107_shop-methodthree" data-toggle="collapse" data-target="#ant107_shop-collapsethree" aria-controls="ant107_shop-collapsethree">Банковский перевод</label>*/}

                {/*                    <div id="ant107_shop-collapsethree" class="collapse" data-parent="#ant107_shop-accordion">*/}
                {/*                        <p>Перераспределение бюджета, безусловно, обуславливает институциональный социальный статус.</p>*/}
                {/*                    </div>*/}

                {/*            </li>*/}
                {/*        </ul>*/}

                {/*        <div class="ant107_shop-checkout-btn text-center">*/}
                {/*            <a href="index.html" class="ant107_shop-theme-btn ant107_shop-no-shadow ant107_shop-br-10 mb-3">Обновить корзину</a>*/}
                {/*            <button class="ant107_shop-theme-btn ant107_shop-br-10">Подтверждаю</button>*/}
                {/*        </div>*/}
                    </div>
                </aside>

            </div>
        </div>
    </div>);

}