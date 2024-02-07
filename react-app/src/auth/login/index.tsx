import React, { FC, useContext, useState} from 'react';
import {Context} from "../../main";
import {Link} from "react-router-dom";
import {observer} from "mobx-react-lite";

import './../../login/vendor/bootstrap/css/bootstrap.min.css'
import './../../login/vendor/animate/animate.css'
import './../../login/vendor/css-hamburgers/hamburgers.min.css'
import './../../login/vendor/select2/select2.min.css'
import './../../login/css/util.css'
import './../../login/css/main.css'
// import './../../login/vendor/jquery/jquery-3.2.1.min.js'
// import './../../login/vendor/bootstrap/js/popper.js'
// import './../../login/vendor/bootstrap/js/bootstrap.min.js'
// import './../../login/vendor/select2/select2.min.js'
// import './../../login/vendor/tilt/tilt.jquery.min.js'
// import './../../login/js/main.js'

const LoginPage: FC=()=>{
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] =useState<string>('');

    const {store} =useContext(Context);

    return(
            <div className="limiter">
                <div className="container-login100">
                    <div className="wrap-login100">
                        <div className="login100-pic js-tilt" data-tilt>
                            <img src="/src/login/images/img-01.png" alt="IMG"/>
                        </div>

                        <div className="login100-form validate-form">
                            <form >
					<span className="login100-form-title">
						Вход
					</span>

                                <div className="wrap-input100 validate-input"
                                     data-validate="Valid email is required: ex@abc.xyz">
                                    <input className="input100"
                                           onChange={e => setUsername(e.target.value)}
                                           value={username}
                                           type="text"
                                           placeholder="Username"
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
                                           placeholder="Password"
                                           required={true}/>
                                    <span className="focus-input100"></span>
                                    <span className="symbol-input100">
							<i className="fa fa-lock" aria-hidden="true"></i>
						</span>
                                </div>
                                </form>

                                <div className="container-login100-form-btn">
                                    <button className="login100-form-btn"
                                            onClick={() => store.login(username, password)}>
                                        Войти
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
                                    <Link to="/register" className="txt2">Создать акаунт
                                        <i className="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                                    </Link>
                                </div>
                        </div>
                    </div>
                </div>
            </div>

        // <div>
        //     <h1>Login page</h1>
        //     <input
        //         onChange={e => setUsername(e.target.value)}
        //         value={username}
        //         type="text"
        //         placeholder="Username"
        //     />
        //
        //     <input
        //         onChange={e => setPassword(e.target.value)}
        //         value={password}
        //         type="password"
        //         placeholder="Password"
        //     />
        //     <button onClick={() => store.login(username, password)}>Войти</button>
        //     <button><Link to="/register">Регистрация</Link></button>
        // </div>

    );

};


export default observer(LoginPage);
