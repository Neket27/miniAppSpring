import React, {useContext, useEffect, useState} from "react";
import {ContextService} from "../../../main";
import UserService from "../../../service/user/UserService";
import {IUserDelivery} from "../../../model/user/IUserDelivery";

const DeliveryUserData=() => {
    const { authService } = useContext(ContextService);
    const [city, setCity] = useState<string>('');
    const [address, setAddress] = useState<string>('');
    const [buildingOfHouse, setBuildingOfHouse] = useState<string>('');
    // const [avatar, setAvatar] = useState<IImage|null|undefined>();
    const [flat, setFlat] = useState<string>('');
    const [valueButton, setValueButton] = useState<string>('Сохранить');
    const [id, setId] = useState<number>();
    async function getDataUserAboutDelivery() {
        const response: IUserDelivery = await UserService.getDataUserAboutDelivery();
        if(response.id!=null) {
            setId(response.id)
            setCity(response.city);
            setAddress(response.address);
            setBuildingOfHouse(response.buildingOfHouse);
            setFlat(response.flat);
        }
    }

    function changeDataUserAboutDelivery() {
        const dataUserDelivery:IUserDelivery ={
            id:id,
            city:city,
            address:address,
            buildingOfHouse:buildingOfHouse,
            flat:flat
        }

            const response = UserService.changeDataUserAboutDelivery(dataUserDelivery);
    }

    useEffect(()=>{
        getDataUserAboutDelivery();
    },[])

    return (
        <div className="limiter">
            <div className="container-change-data-user100">
                <div className="wrap-login100">
                    <input id="fileInput" className="login100-pic js-tilt" data-tilt type="file" style={{display: 'none'}} onChange={(event) => handleFileChange(event.target.files[0])} />
                    {/*<div className="login100-pic js-tilt" data-tilt onClick={handleImageClick}>*/}
                    {/*    {avatar != null ? <img src={"data:image/png;base64," + avatar.base64} alt="Avatar" /> : <img src="/src/login/images/img-01.png" alt="IMG" />}*/}
                    {/*</div>*/}

                    <div className="login100-form validate-form">
                        <h5 className="text-center text-secondary mb-3">
                            Ваши данные о доставки
                        </h5>
                        <form>

                            <div className="wrap-input100 validate-input"
                                 data-validate="Valid email is required: ex@abc.xyz">
                                <input className="input100" onChange={e => setCity(e.target.value)} value={city}
                                       type="text" placeholder="Город" required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                                    <i className="fa fa-map-marker" aria-hidden="true"></i>
                                </span>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Password is required">
                                <input className="input100" onChange={e => setAddress(e.target.value)} value={address}
                                       type="text" placeholder="Улица" required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                                    <i className="fa fa-road" aria-hidden="true"></i>
                                </span>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Password is required">
                                <input className="input100" onChange={e => setBuildingOfHouse(e.target.value)}
                                       value={buildingOfHouse} type="text" placeholder="Корпус" required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                                    <i className="fa fa-building" aria-hidden="true"></i>
                                </span>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Password is required">
                                <input className="input100" onChange={e => setFlat(e.target.value)}
                                       value={flat} type="text" placeholder="Квартира" required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                                    <i className="fa fa-home" aria-hidden="true"></i>
                                </span>
                            </div>

                        </form>

                        <div className="container-login100-form-btn">
                            <button className="login100-form-btn ant107_shop-theme-btn ant107_shop-br-10"
                                    onClick={() => {
                                        changeDataUserAboutDelivery();
                                        setValueButton("Сохранено");
                                        setTimeout(() => setValueButton('Сохранить'), 3000);
                                    }}>
                                {valueButton}
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default DeliveryUserData;