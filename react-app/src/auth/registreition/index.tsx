import React, {useContext, useState} from 'react';
import {Context} from "../../main";
import {Link,} from "react-router-dom";
import {observer} from "mobx-react-lite";


const RegisterPage=()=>{
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] =useState<string>('');

    const {store} =useContext(Context);
    return(
        <div>
            <h1>Register page</h1>
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
            <button onClick={() => store.registration(username, password)}>Зарегистрироваться</button>
            <button>  <Link to="/login">Войти</Link></button>
        </div>
    );

};

export default observer(RegisterPage);

