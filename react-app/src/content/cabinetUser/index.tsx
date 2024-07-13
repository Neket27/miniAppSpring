import React, {useContext, useEffect} from "react";
import {ContextService, State} from "../../main";
import {ContextCountProductInBag} from "../navbar";
import {Link, useNavigate} from 'react-router-dom';

const CabinetUser = () => {
    const navigate = useNavigate();
    const contextService:State= useContext(ContextService);
    const contextCountProductInBag =useContext(ContextCountProductInBag);

    useEffect(() => {
        if (!contextService.authService.isAuth)
            navigate('/');
    }, [navigate]);

    return (
        <div>
            <h1 className="auth">Привет, {contextService.authService.user.firstname}.</h1>

                <button className="ant107_shop-theme-btn ant107_shop-no-shadow ant107_shop-bg-black ant107_shop-br-10 ml-3"
                        type="submit" onClick={async () => {
                    const logout: boolean | undefined = await contextService.authService.logout();
                    if (logout == true)
                        contextCountProductInBag.setCountProductInBag(0);
                    window.location.reload();
                }}>
                    Выйти
                </button>


            <button className="ant107_shop-theme-btn ant107_shop-no-shadow ant107_shop-bg-black ant107_shop-br-10 ml-3"
                    type="submit">
                <Link to="/" className="ant107_shop-theme-btn ant107_shop-br-10">Изменить пароль</Link>
            </button>

            <button className="ant107_shop-theme-btn ant107_shop-no-shadow ant107_shop-bg-black ant107_shop-br-10 ml-3"
                    type="submit">
                <Link to="/cabinetUser/chageData" className="ant107_shop-theme-btn ant107_shop-br-10">Изменить данные о себе</Link>
            </button>
        </div>
    );
}

export default CabinetUser;