import React, { useContext, useState } from 'react';
import { ContextService } from "../../../main";
import '../../../../css/ResetPassword.css'; // Не забудьте создать этот файл

const PasswordUpdate = () => {
    const [password, setPassword] = useState<string>('');
    const [newPassword, setNewPassword] = useState<string>('');

    const { authService } = useContext(ContextService);

    const UpdatePassword = async () => {
        const success = await authService.resetPassword(password, newPassword);
        if (success) {
            alert('Пароль успешно обновлён!');
            // Здесь можно добавить логику, например, редирект на другую страницу
        } else {
            alert('Ошибка при обновлении пароля. Попробуйте еще раз.');
        }
    };

    return (
        <div className="reset-password-container">
            <div className="reset-password-wrapper">
                <h1 className="reset-password-title">Изменение пароля</h1>
                <input
                    onChange={e => setPassword(e.target.value)}
                    value={password}
                    type="password"
                    placeholder="Текущий пароль"
                    className="reset-password-input"
                />
                <input
                    onChange={e => setNewPassword(e.target.value)}
                    value={newPassword}
                    type="password"
                    placeholder="Новый пароль"
                    className="reset-password-input"
                />
                <button onClick={UpdatePassword} className="reset-password-button">Обновить пароль</button>
            </div>
        </div>
    );
}

export default PasswordUpdate;
