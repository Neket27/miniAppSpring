import React from "react";
import '../../../css/Refund.css';

const Refund = () => {
    return (
        <div className="about-container">
            <div className="about-wrapper">
                <h1 className="about-title">🔁 Обмен, возврат и гарантия</h1>

                <p className="about-text">
                    Мы всегда стремимся к тому, чтобы вы остались довольны покупкой. Если что-то пошло не так — вы
                    можете подать заявку на обмен, возврат или гарантийное обслуживание с помощью формы ниже.
                </p>

                <form className="return-form" onSubmit={(e) => e.preventDefault()}
                      style={{display: 'flex', flexDirection: 'column', gap: '15px', marginTop: '20px'}}>
                    <label className="about-text">
                        Ваша почта:
                        <input type="email" name="email" required placeholder="example@mail.com"
                               style={{padding: '10px', marginTop: '5px', width: '100%'}}/>
                    </label>

                    <label className="about-text">
                        Причина обращения:
                        <textarea name="message" required placeholder="Опишите проблему или причину возврата" style={{
                            padding: '10px',
                            marginTop: '5px',
                            width: '100%',
                            height: '100px'
                        }}></textarea>
                    </label>

                    <label className="support-file-upload">
                        Прикрепить фото/документы
                        <input
                            type="file"
                            // onChange={handleImageUpload}
                            accept="image/*"
                            multiple
                            className="support-file-input"
                        />
                    </label>

                    <button type="submit" style={{
                        padding: '12px 20px',
                        backgroundColor: '#333',
                        color: '#fff',
                        border: 'none',
                        borderRadius: '5px',
                        cursor: 'pointer'
                    }}>
                        Отправить заявку
                    </button>
                </form>
            </div>
        </div>


    );
};

export default Refund;
