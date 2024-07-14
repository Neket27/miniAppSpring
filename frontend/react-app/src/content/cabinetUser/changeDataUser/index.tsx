import {Link} from "react-router-dom";
import React, {useContext, useEffect, useState} from "react";
import {ContextService} from "../../../main";
import {IUser} from "../../../model/user/IUser";
import UserService from "../../../service/user/UserService";
import {IUpdateDataUserInCabinet} from "../../../model/user/IUpdateDataUserInCabinet";
import {IImage} from "../../../model/product/IImage";

const ChangeDataUser = () => {
    const { authService } = useContext(ContextService);
    const [firstname, setFirstname] = useState<string>('');
    const [lastname, setLastname] = useState<string>('');
    const [email, setEmail] = useState<string>('');
    const [avatar, setAvatar] = useState<IImage|null|undefined>();
    const [valueButton, setValueButton] = useState<string>('Изменить');

    const changeData = async () => {
        const accessToken = localStorage.getItem('accessToken');
        if (accessToken) {
            const userData: IUpdateDataUserInCabinet = {
                accessToken: accessToken,
                firstname: firstname,
                lastname: lastname,
                email: email,
                avatar: avatar
            };

            console.log("выполнено");
            const response: IUpdateDataUserInCabinet = await UserService.updateDataUser(userData);
            setFirstname(response.firstname);
            setLastname(response.lastname);
            setEmail(response.email);
            if (response.avatar)
                setAvatar(response.avatar);

            console.log("выполнено и получено");
        }
    };

    const handleFileChange = (file: File) => {
        const avatar: IImage = {} as IImage;
        avatar.name = file.name;
        avatar.contentType = file.type;

        const reader = new FileReader();
        reader.onloadend = () => {
            if (typeof reader.result === "string"){
                avatar.base64 = reader.result.split(',')[1];
            setAvatar(avatar);
            }
        };
        reader.readAsDataURL(file);
    };

    const handleImageClick = () => {
        document.getElementById("fileInput").click();
    };

    useEffect(() => {
        const user: IUser = authService.user;
        setFirstname(user.firstname);
        setLastname(user.lastname);
        setEmail(user.email);
        setAvatar(user.avatar);
    }, []);

    return (
        <div className="limiter">
            <div className="container-change-data-user100">
                <div className="wrap-login100">
                    <input id="fileInput" className="login100-pic js-tilt" data-tilt type="file" style={{display: 'none'}} onChange={(event) => handleFileChange(event.target.files[0])} />
                    <div className="login100-pic js-tilt" data-tilt onClick={handleImageClick}>
                        {avatar != null ? <img src={"data:image/png;base64," + avatar.base64} alt="Avatar" /> : <img src="/src/login/images/img-01.png" alt="IMG" />}
                    </div>

                    <div className="login100-form validate-form">
                        <form>
                            <span className="login100-form-title">
                                Ваши данные
                            </span>

                            <div className="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
                                <input className="input100" onChange={e => setFirstname(e.target.value)} value={firstname} type="text" placeholder="Имя" required={false} />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                                    <i className="fa fa-envelope" aria-hidden="true"></i>
                                </span>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Password is required">
                                <input className="input100" onChange={e => setLastname(e.target.value)} value={lastname} type="text" placeholder="Фамилия" required={false} />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                                    <i className="fa fa-lock" aria-hidden="true"></i>
                                </span>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Password is required">
                                <input className="input100" onChange={e => setEmail(e.target.value)} value={email} type="text" placeholder="Email" required={false} />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                                    <i className="fa fa-lock" aria-hidden="true"></i>
                                </span>
                            </div>
                        </form>

                        <div className="container-login100-form-btn">
                            <button className="login100-form-btn ant107_shop-theme-btn ant107_shop-br-10" onClick={() => {
                                changeData();
                                setValueButton("Данные отправлены");
                                setTimeout(() => setValueButton('Изменить'), 3000);
                            }}>
                                {valueButton}
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ChangeDataUser;
