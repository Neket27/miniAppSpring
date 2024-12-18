import {useContext, useState} from "react";
import {ContextService} from "../../../main";
import {ICreateCoupon} from "../../../model/coupon/ICreateCoupon";
import {ICoupon} from "../../../model/coupon/ICoupon";

export const AddCoupon = () => {
    const context = useContext(ContextService);
    const [title, setTitle] = useState<string>('');
    const [amount, setAmount] = useState<number>(0);
    const [timeLive, setTimeLive] = useState<number>(0);
    const [titleCouponAdd, setTitleCouponAdd] = useState<string>('');
    const createCoupon = async () => {
        const coupon: ICreateCoupon = {
            title: title,
            amount: amount,
            timeLiveInHour: timeLive,
        };
        const couponResponse:ICoupon = await context.couponService.createCoupon(coupon);
        if(couponResponse){
            setTitleCouponAdd('Купон добавлен');
            setTimeout(()=>setTitleCouponAdd(""),4000);
        }else {
            setTitleCouponAdd('Ошибка создания купона');
            setTimeout(()=>setTitleCouponAdd(""),4000);
        }

    }

    return (<div className="limiter">


        <div className="container-login100">
            <div className="wrap-login100">
                <div className="login100-form validate-form">
                    <h5 className="text-center mb-3">
                        Создание купона
                    </h5>
                    <form onSubmit={e => {
                        e.preventDefault();
                        createCoupon();
                    }}>
                        <div className="wrap-input100 validate-input" data-validate="Password is required">
                            <input className="input100"
                                   onChange={e => setTitle(e.target.value)}
                                   value={title}
                                   type="text"
                                   placeholder="Название купона"
                                   required={true}/>
                            <span className="focus-input100"></span>
                            <span className="symbol-input100">
							<i className="fa fa-ticket" aria-hidden="true"></i>
						</span>
                        </div>

                        <div className="wrap-input100 validate-input"
                             data-validate="Valid email is required: ex@abc.xyz">
                            <input className="input100"
                                   onChange={e => setAmount(parseInt(e.target.value))}
                                   value={amount || ''}
                                   type="number"
                                   placeholder="Сумма купона"
                                   required={true}/>
                            <span className="focus-input100"></span>
                            <span className="symbol-input100">
							<i className="fa fa-money" aria-hidden="true"></i>
						</span>
                        </div>

                        <div className="wrap-input100 validate-input"
                             data-validate="Valid email is required: ex@abc.xyz">
                            <input className="input100"
                                   onChange={e => setTimeLive(parseInt(e.target.value))}
                                   value={timeLive || ''}
                                   type="number"
                                   placeholder="Срок действия купона в часах"
                                   required={true}/>
                            <span className="focus-input100"></span>
                            <span className="symbol-input100">
							<i className="fa fa-clock-o" aria-hidden="true"></i>
						</span>
                        </div>
                        <div className="container-login100-form-btn">
                            <button className="login100-form-btn">

                                Создать
                            </button>
                        </div>
                        <p>{titleCouponAdd}</p>
                    </form>
                </div>
            </div>
        </div>
    </div>);
}