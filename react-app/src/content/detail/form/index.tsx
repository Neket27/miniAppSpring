const FormForDetailProduct =()=> {
    return <form id="ant107_shop-contact-form" className="ant107_shop-contact-form"
                 action="#" method="POST">
        <div className="row clearfix">
            <div className="col-md-6">
                <div className="form-group">
                    <label htmlFor="ant107_shop-name">Ваше имя</label>
                    <input type="text" name="first_name" id="ant107_shop-name"
                           className="form-control" value=""
                           placeholder="Иван Дионтьев" required/>
                </div>
            </div>
            <div className="col-md-6">
                <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input type="ant107_shop-email" name="email"
                           id="ant107_shop-email" className="form-control" value=""
                           placeholder="yourmail@gmail.com" required/>
                </div>
            </div>
            <div className="col-md-12">
                <div className="form-group">
                    <label htmlFor="ant107_shop-message">Сообщение</label>
                    <textarea name="message" id="ant107_shop-message"
                              className="form-control" rows={3}
                              placeholder="Что Вы хотите сказать"
                              required></textarea>
                </div>
            </div>
            <div className="col-md-4 d-flex">
                <div className="ant107_shop-your-rating d-flex align-items-center">
                    <h6 className="mb-0 mr-2">Ваша оценка:</h6>
                    <div className="ant107_shop-ratings"
                         id="ant107_shop-your-rating">
                        <i className="fas fa-star"></i>
                        <i className="fas fa-star"></i>
                        <i className="fas fa-star"></i>
                        <i className="fas fa-star"></i>
                        <i className="fas fa-star"></i>
                    </div>
                </div>
            </div>
            <div className="col-md-4">
                <div className="ant107_shop-upload-btn-wrapper">
                    <button className="ant107_shop-upload-btn">
                        <span><i className="fas fa-file-image"></i>Вложения</span>
                    </button>
                    <input type="file" name="myfile"/>
                </div>
            </div>
            <div className="col-md-4">
                <div className="form-group text-left text-md-right mb-0">
                    <button className="ant107_shop-theme-btn ant107_shop-br-30"
                            type="submit">Отправить
                    </button>
                </div>
            </div>
        </div>
    </form>

}

export default FormForDetailProduct;