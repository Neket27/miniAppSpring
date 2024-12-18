import { useContext, useState } from "react";
import {ContextService, State} from "../../../main";
import {Link, useNavigate} from "react-router-dom";
import { observer } from "mobx-react-lite";

const RegisterPage = () => {
    const navigate = useNavigate();
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [passwordRepeat, setPasswordRepeat] = useState<string>('');
    const [showTemporaryBlock, setShowTemporaryBlock] = useState<boolean>(false);
    const contextService:State = useContext(ContextService);

    const handleRegistrationClick = () => {
        if (passwordRepeat === password)
            contextService.authService.registration(username, password);
        else {
            setShowTemporaryBlock(true);
            setTimeout(() => {
                setShowTemporaryBlock(false);
            }, 3000);
        }
        navigate('/');
    };

    return (
        <div className="limiter">
            <div className="container-login100">
                <div className="wrap-login100">
                    <div className="login100-pic js-tilt" data-tilt>
                        <img src="/src/login/images/img-01.png" alt="IMG" />
                    </div>
                    <div className="login100-form validate-form">
                        <h5 className="text-center text-secondary mb-3">
                            Регистрация
                        </h5>
                        <form>

                            <div className="wrap-input100 validate-input"
                                 data-validate="Valid email is required: ex@abc.xyz">
                                {/*<div className="input_auth">*/}
                                    <input className="input100"
                                           onChange={e => setUsername(e.target.value)}
                                           value={username}
                                           type="text"
                                           placeholder="Имя пользователя"
                                           required={true}/>
                                    <span className="focus-input100"></span>
                                    <span className="symbol-input100">
                                    <i className="fa fa-user" aria-hidden="true"></i>
                                </span>
                                {/*</div>*/}
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Password is required">
                                {/*<div className="input_auth">*/}
                                <input className="input100"
                                           onChange={e => setPassword(e.target.value)}
                                           value={password}
                                           type="password"
                                           placeholder="Пароль"
                                           required={true}/>
                                    <span className="focus-input100"></span>
                                    <span className="symbol-input100">
                                        <i className="fa fa-lock" aria-hidden="true"></i>
                                    </span>
                                {/*</div>*/}
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Password is required">
                                {/*<div className="input_auth">*/}
                                    <input className="input100"
                                           onChange={e => setPasswordRepeat(e.target.value)}
                                           value={passwordRepeat}
                                           type="password"
                                           placeholder="Повторите пароль"
                                           required={true}/>
                                    <span className="focus-input100"></span>
                                    <span className="symbol-input100">
                                        <i className="fa fa-lock" aria-hidden="true"></i>
                                    </span>
                                {/*</div>*/}

                            </div>
                        </form>
                        {showTemporaryBlock && (
                            <div className="temporary-block">
                                <span>Пароли не совпадают</span>
                            </div>
                        )}

                        <div className="container-login100-form-btn">
                            <div className="input_auth">
                            <button className="login100-form-btn" onClick={handleRegistrationClick}>
                                Зарегистрироваться
                            </button>
                        </div>
                        </div>

                        <div className="text-center p-t-12">
                            <span className="txt1">
                                Забыли &nbsp;
                            </span>
                            <a className="txt2" href="#">
                                Имя пользователя / Пароль?
                            </a>
                        </div>

                        <div className="text-center p-t-136">
                            <Link to="/login" className="txt2">Войти
                                <i className="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default observer(RegisterPage);
