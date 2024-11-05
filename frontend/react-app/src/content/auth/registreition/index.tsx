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
                        <form>
                            <span className="login100-form-title">
                                Регистрация
                            </span>

                            <div className="wrap-input100 validate-input"
                                 data-validate="Valid email is required: ex@abc.xyz">
                                <input className="input100"
                                       onChange={e => setUsername(e.target.value)}
                                       value={username}
                                       type="text"
                                       placeholder="Username"
                                       required={true} />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                                    <i className="fa fa-envelope" aria-hidden="true"></i>
                                </span>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Password is required">
                                <input className="input100"
                                       onChange={e => setPassword(e.target.value)}
                                       value={password}
                                       type="password"
                                       placeholder="Password"
                                       required={true} />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                                    <i className="fa fa-lock" aria-hidden="true"></i>
                                </span>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Password is required">
                                <input className="input100"
                                       onChange={e => setPasswordRepeat(e.target.value)}
                                       value={passwordRepeat}
                                       type="password"
                                       placeholder="Repeat password"
                                       required={true} />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                                    <i className="fa fa-lock" aria-hidden="true"></i>
                                </span>
                            </div>
                        </form>
                        {showTemporaryBlock && (
                            <div className="temporary-block">
                                <span>Пароли не совпадают</span>
                            </div>
                        )}

                        <div className="container-login100-form-btn">
                            <button className="login100-form-btn" onClick={handleRegistrationClick}>
                                Зарегистрироваться
                            </button>
                        </div>

                        <div className="text-center p-t-12">
                            <span className="txt1">
                                Forgot
                            </span>
                            <a className="txt2" href="#">
                                Username / Password?
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
