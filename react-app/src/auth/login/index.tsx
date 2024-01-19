import React, { FC, useContext, useState} from 'react';
import {Context} from "../../main";
import {Link} from "react-router-dom";
import {observer} from "mobx-react-lite";


const LoginPage: FC=()=>{
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] =useState<string>('');

    const {store} =useContext(Context);

    return(
        <div>
            <h1>Login page</h1>
            <input
                onChange={e => setUsername(e.target.value)}
                value={username}
                type="text"
                placeholder="Username"
            />

            <input
                onChange={e => setPassword(e.target.value)}
                value={password}
                type="password"
                placeholder="Password"
            />
            <button onClick={() => store.login(username, password)}>Войти</button>
            <button><Link to="/register">Регистрация</Link></button>
        </div>
    );

};


export default observer(LoginPage);
