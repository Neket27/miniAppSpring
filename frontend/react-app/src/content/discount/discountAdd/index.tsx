import {useContext, useState} from "react";
import {ContextService} from "../../../main";
import {IDiscount} from "../../../model/discount/IDiscount";
import {IDiscountCreate} from "../../../model/discount/IDiscountCreate";

export const DiscountAdd = () => {
    const context = useContext(ContextService);
    const [name, setName] = useState<string>('');
    const [amount, setAmount] = useState<number>(0);
    const [timeLive, setTimeLive] = useState<number>(0);
    const [city, setCity] = useState<string>('');
    const [productIdList, setProductIdList] = useState<number[]>([]);
    const [titleDiscountAdd, setTitleDiscountAdd] = useState<string>('');
    const [valueInputProductId, setValueInputProductId] = useState<string>('')
    const createDiscount = async () => {
        const discount: IDiscountCreate = {
            name: name,
            amount: amount,
            timeLiveInHour: timeLive,
            city:city,
            productIdList: productIdList,
        };
        const couponResponse:IDiscount = await context.discountService.createDiscount(discount);
        if(couponResponse){
            setTitleDiscountAdd('Скадка создана');
            setTimeout(()=>setTitleDiscountAdd(""),4000);
        }else {
            setTitleDiscountAdd('Ошибка создания скидки');
            setTimeout(()=>setTitleDiscountAdd(""),4000);
        }

    }

    return (<div className="limiter">
            <div className="container-login100">
                <div className="wrap-login100">
                    <div className="login100-form validate-form">
                        <h5 className="text-center mb-3">
                            Создание скидки
                        </h5>
                        <form onSubmit={e => {
                            e.preventDefault();
                            createDiscount();
                        }}>

                            <div className="wrap-input100 validate-input" data-validate="Название скидки обязательно">
                                <input
                                    className="input100"
                                    onChange={e => setName(e.target.value)}
                                    value={name}
                                    type="text"
                                    placeholder="Название скидки"
                                    required
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                        <i className="fa fa-tag" aria-hidden="true"></i>
                    </span>
                            </div>

                            {/* Сумма скидки */}
                            <div className="wrap-input100 validate-input" data-validate="Сумма скидки обязательна">
                                <input
                                    className="input100"
                                    onChange={e => setAmount(parseInt(e.target.value))}
                                    value={amount || ''}
                                    type="number"
                                    placeholder="Сумма скидки"
                                    required
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                        <i className="fa fa-percent" aria-hidden="true"></i>
                    </span>
                            </div>

                            {/* Срок действия скидки */}
                            <div className="wrap-input100 validate-input"
                                 data-validate="Срок действия скидки обязателен">
                                <input
                                    className="input100"
                                    onChange={e => setTimeLive(parseInt(e.target.value))}
                                    value={timeLive || ''}
                                    type="number"
                                    placeholder="Срок действия скидки в часах"
                                    required
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                        <i className="fa fa-clock-o" aria-hidden="true"></i>
                    </span>
                            </div>

                            {/* Город действия скидки */}
                            <div className="wrap-input100 validate-input" data-validate="Город обязателен">
                                <input
                                    className="input100"
                                    onChange={e => setCity(e.target.value)}
                                    value={city}
                                    type="text"
                                    placeholder="Город действия скидки"
                                    required
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                        <i className="fa fa-map-marker" aria-hidden="true"></i>
                    </span>
                            </div>

                            {/* ID продуктов */}
                            <div className="wrap-input100 validate-input" data-validate="ID продуктов обязательны">
                                <input
                                    className="input100"
                                    onChange={e => {
                                        const value = e.target.value;
                                        setValueInputProductId(value);
                                        const idProducts = value
                                            .split(',')
                                            .map(val => parseInt(val))
                                            .filter(Number.isInteger);
                                        setProductIdList(idProducts);
                                    }}
                                    value={valueInputProductId || ''}
                                    type="text"
                                    placeholder="ID продуктов к которым применится скидка"
                                    required
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                        <i className="fa fa-list-alt" aria-hidden="true"></i>
                    </span>
                            </div>

                            {/* Кнопка создания скидки */}
                            <div className="container-login100-form-btn">
                                <button className="login100-form-btn">
                                    Создать
                                </button>
                            </div>

                            {/* Сообщение о результате */}
                            <p>{titleDiscountAdd}</p>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}
