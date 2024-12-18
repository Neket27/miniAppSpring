import { FC, useContext, useState,useEffect} from 'react';
import {ContextService} from "../../../main";
import {Link, useLocation, useNavigate} from "react-router-dom";
import {observer} from "mobx-react-lite";

import '../../../login/vendor/bootstrap/css/bootstrap.min.css'
import '../../../login/vendor/animate/animate.css'
import '../../../login/vendor/css-hamburgers/hamburgers.min.css'
import '../../../login/vendor/select2/select2.min.css'
import '../../../login/css/util.css'
import '../../../login/css/main.css'

const LoginPage: FC=()=>{
    const navigate = useNavigate();
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] =useState<string>('');

    const [showTemporaryBlock, setShowTemporaryBlock] = useState<boolean>(false);
    const [validDate, setValidDate] = useState<string>('');

    const {authService,bagService} =useContext(ContextService);


    const handleLogin =async () => {
       const responseLogin:string|undefined = await authService.login(username, password);
       await bagService.getCountProductInBag();

        if (responseLogin!=null||responseLogin!=undefined) {
            setValidDate(responseLogin);

            setShowTemporaryBlock(true);
            setTimeout(() => {
                setShowTemporaryBlock(false);
            }, 3000);
        }
        if(responseLogin!=String(403))
            navigate("/");
    }

    return(
            <div className="limiter">
                <div className="container-login100">
                    <div className="wrap-login100">
                        <div className="login100-pic js-tilt" data-tilt>
                            <img src="/src/login/images/img-01.png" alt="IMG"/>
                        </div>

                        <div className="login100-form validate-form">
                            <h5 className="text-center text-secondary mb-3">
                                Вход
                            </h5>
                            <form>
                                <div className="wrap-input100 validate-input"
                                     data-validate="Valid email is required: ex@abc.xyz">
                                    <input className="input100"
                                           onChange={e => setUsername(e.target.value)}
                                           value={username}
                                           type="text"
                                           placeholder="Имя пользователя"
                                           required={true}/>
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
                                           placeholder="Пароль"
                                           required={true}/>
                                    <span className="focus-input100"></span>
                                    <span className="symbol-input100">
							<i className="fa fa-lock" aria-hidden="true"></i>
						</span>
                                </div>
                            </form>

                            <div className="container-login100-form-btn">
                                <button className="login100-form-btn"
                                        onClick={() => handleLogin()}>
                                    Войти
                                </button>
                                {showTemporaryBlock && (<p className="txt2">{validDate}</p>)}
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
                                <Link to="/register" className="txt2">Создать акаунт
                                    <i className="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    );

};


export default observer(LoginPage);
