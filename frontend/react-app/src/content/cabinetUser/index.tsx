import React, { useContext, useEffect, useState } from "react";
import { ContextService, State } from "../../main";
import { ContextCountProductInBag, CountProductInBag } from "../navbar";
import { Link, useNavigate } from "react-router-dom";
import '../../../css/CabinetUser.css';

const CabinetUser = () => {
    const navigate = useNavigate();
    const contextService: State = useContext(ContextService);
    const contextCountProductInBag: CountProductInBag = useContext(ContextCountProductInBag);
    const [flagContentAdmin, setFlagContentAdmin] = useState<boolean>(false);

    const getUserRoles = async () => {
        const response: Array<string> = await contextService.authService.getUserRoles(contextService.authService.user.username);
        if (response.includes("ROLE_ADMIN")||response.includes("ROLE_EMPLOYEE"))
            setFlagContentAdmin(true);
    };

    useEffect(() => {
        if (!contextService.authService.isAuth) navigate('/');
        getUserRoles();
    }, [navigate]);

    return (
        <div className="container">
            <div className="wrapper">
                <div className="cabinet-container">
                    <h1 className="cabinet-welcome">Привет, {contextService.authService.user.firstname}.</h1>
                    <div className="cabinet-buttons">
                        <button
                            className="cabinet-btn" // Обновлено имя класса для кнопки
                            onClick={async () => {
                                const logout: boolean | undefined = await contextService.authService.logout();
                                if (logout) contextCountProductInBag.setCountProductInBag(0);
                                window.location.reload();
                            }}
                        >
                            Выйти
                        </button>
                        <Link to="/resetPassword" className="cabinet-link">
                            Изменить пароль
                        </Link>
                        <Link to="/cabinetUser/changeData" className="cabinet-link">
                            Данные о себе
                        </Link>
                        <Link to="/cabinetUser/delivery" className="cabinet-link">
                            Данные о доставки
                        </Link>
                        {flagContentAdmin && (
                            <>
                                <Link to="/category/update" className="cabinet-link">
                                    Изменить категории
                                </Link>
                                <Link to="/product/add" className="cabinet-link">
                                    Добавить продукт
                                </Link>
                                <Link to="/coupon/add" className="cabinet-link">
                                    Добавить купон
                                </Link>
                                <Link to="/discount/create" className="cabinet-link">
                                    Создать скидку
                                </Link>
                            </>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default CabinetUser;
