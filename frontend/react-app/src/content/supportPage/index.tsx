import React, {useContext, useState} from 'react';
import '../../../css/SupportPage.css';
import {IImage} from "../../model/product/IImage";
import {ICreateSupportMessage} from "../../model/support/ICreateSupportMessage";
import {ContextService} from "../../main";


const SupportPage = () => {
    const context = useContext(ContextService);
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [message, setMessage] = useState('');
    const [images, setImages] = useState<IImage[]>([]);

    const addHelpMessage= async ()=>{
        const createSupportMessage:ICreateSupportMessage = {
            nameUser:name,
            email:email,
            message:message,
            imageDtoList:images,
        }
          const response = await context.supportService.addSupportMessage(createSupportMessage);
        console.log(response);
    }

    const handleImageUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
        const files = e.target.files;
        if (files) {
            Array.from(files).forEach((file) => {
                const reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = () => {
                    if (reader.result) {
                        setImages((prevImages) => [
                            ...prevImages,
                            {
                                name: file.name,
                                contentType: file.type,
                                base64: reader.result.toString().split(',')[1],
                            },
                        ]);
                    }
                };
            });
        }
    };

    const removeImage = (index: number) => {
        setImages(images.filter((_, i) => i !== index));
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        // Логика отправки данных, включая изображения, на сервер
        alert('Ваше сообщение и фотографии отправлены. Мы свяжемся с вами в ближайшее время.');
        setName('');
        setEmail('');
        setMessage('');
        setImages([]);
    };

    return (
        <div className="support-container">
            <div className="support-wrapper">
                <h1 className="support-title">Поддержка</h1>
                <p className="support-description">
                    Если у вас есть вопросы или нужна помощь, пожалуйста, заполните форму ниже.
                    Наша команда поддержки свяжется с вами в ближайшее время!
                </p>
                <form onSubmit={handleSubmit} className="support-form">
                    <input
                        type="text"
                        placeholder="Ваше имя"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        className="support-input"
                        required
                    />
                    <input
                        type="email"
                        placeholder="Ваш Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="support-input"
                        required
                    />
                    <textarea
                        placeholder="Ваше сообщение"
                        value={message}
                        onChange={(e) => setMessage(e.target.value)}
                        className="support-textarea"
                        rows={5}
                        required
                    />
                    <label className="support-file-upload">
                        Прикрепить изображения
                        <input
                            type="file"
                            onChange={handleImageUpload}
                            accept="image/*"
                            multiple
                            className="support-file-input"
                        />
                    </label>
                    <div className="support-image-preview">
                        {images.map((image, index) => (
                            <div key={index} className="support-image-item">
                                <img src={"data:image/png;base64,"+image.base64 as string} alt={image.name} className="support-image" />
                                <button type="button" onClick={() => removeImage(index)} className="support-remove-image">
                                    Удалить
                                </button>
                            </div>
                        ))}
                    </div>
                    <button type="submit" className="support-button"
                    onClick={()=>addHelpMessage()}
                    >Отправить сообщение</button>
                </form>
            </div>
        </div>
    );
};

export default SupportPage;
