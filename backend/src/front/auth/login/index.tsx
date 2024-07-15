import React, { FC, useContext, useState} from 'react';


const LoginPage: FC=()=>{
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] =useState<string>('');

    // @ts-ignore
    const {store} =useContext(Context);

    return(
        <div>
            <h1>Login page</h1>
            <input
            onChange={e=> setUsername(e.target.value)}
            value={username}
            type="text"
            placeholder="Username"
            />

            <input
                onChange={e=> setPassword(e.target.value)}
                value={password}
                type="password"
                placeholder="Password"
            />
            <button onClick={()=>store.login(username,password)}>Логин</button>
            <button onClick={()=>store.registration(username,password)}>Регистрация</button>
        </div>
    );

};


export default LoginPage;
