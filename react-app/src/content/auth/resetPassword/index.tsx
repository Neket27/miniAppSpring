import React, {useContext, useState} from 'react';
import {ContextService} from "../../../main";

const ResetPassword=()=> {
    const [password, setUsername] = useState<string>('');
    const [newPassword, setPassword] =useState<string>('');

    const {authService} =useContext(ContextService);
    return (
        <div>
            <h1>ResetPassword page</h1>
                <input
                    onChange={e => setUsername(e.target.value)}
                    value={password}
                    type="text"
                    placeholder="Текущий пароль"
                />

                <input
                    onChange={e => setPassword(e.target.value)}
                    value={newPassword}
                    type="text"
                    placeholder="Новый пароль"
                />
                <button onClick={() => authService.resetPassword(password,newPassword)}>Обновить пороль</button>
        </div>
    );
}
export default ResetPassword;