import {SetStateAction, useState} from "react";
import {Rating} from "../../rating/Rating";
import ProductService from "../../../product/service/productService";
import {IFeedback} from "../../rating/model/IFeedback";
import {IProductCart} from "../../../product/model/IProductCart";


const FormForDetailProduct = (props: { idProduct: number|undefined; }) => {

    const [name, setName] = useState<string>('');
    const [email, setEmail] = useState<string>('');
    const [message, setMessage] = useState<string>('');
    const [images, setImages] = useState<Array<string>>([]);
    const [evaluation, setEvaluation] = useState<number>(0);
    async function addFeedback(idProduct:number|undefined){
        const rating:IFeedback ={
            // @ts-ignore
            idProduct:idProduct,
            nameUser:name,
            email:email,
            message:message,
            evaluation:evaluation,
            imageList:images
        }
        const response = await ProductService.addRating(rating);
        console.log("Ответ")
        console.log(response)
    }

    const handleFileChange = (event: { target: { files: any; }; }) => {
        const files = event.target.files;
        console.log("files= "+files)
        if (files.length > 0) {
            const imageArray: SetStateAction<any[]> = [];

            // Проходим по массиву файлов и читаем их содержимое
            Array.from(files).forEach((file) => {
                const reader = new FileReader();
                reader.onloadend = () => {
                    // Сохраняем содержимое файла в массиве
                    // @ts-ignore
                    const imageDataWithoutPrefix = reader.result.replace(/^data:image\/(jpeg|jpg|png);base64,/, '');
                    // @ts-ignore
                    console.log(reader.result.split(',')[1])
                    console.log("__________________________________________________________________________________________________");

                    imageArray.push(imageDataWithoutPrefix);

                    // Если все файлы обработаны, обновляем состояние
                    if (imageArray.length === files.length) {
                        setImages(imageArray);
                    }
                };
                reader.readAsDataURL(file as Blob);
            });
        }
    };

    const ratingChanged = (value: SetStateAction<number>) => {
        setEvaluation(value);
    }

                return <form id="ant107_shop-contact-form" className="ant107_shop-contact-form"
                     action="#" method="POST">
            <div className="row clearfix">
                <div className="col-md-6">
                    <div className="form-group">
                        <label htmlFor="ant107_shop-name">Ваше имя</label>
                        <input type="text" name="first_name" id="ant107_shop-name"
                               className="form-control"
                               value={name}
                               onChange={(e) => setName(e.target.value)}
                               placeholder="Иван Дионтьев" required/>
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <input type="ant107_shop-email" name="email"
                               id="ant107_shop-email" className="form-control"
                               value={email}
                               onChange={(e) => setEmail(e.target.value)}
                               placeholder="yourmail@gmail.com" required/>
                    </div>
                </div>
                <div className="col-md-12">
                    <div className="form-group">
                        <label htmlFor="ant107_shop-message">Сообщение</label>
                        <textarea name="message" id="ant107_shop-message"
                                  className="form-control" rows={3}
                                  placeholder="Что Вы хотите сказать"
                                  required
                                  value={message}
                                  onChange={(e) => setMessage(e.target.value)}>
                    </textarea>
                    </div>
                </div>
                <div className="col-md-4 d-flex">
                    <div className="ant107_shop-your-rating d-flex align-items-center">
                        <h6 className="mb-0 mr-2">Ваша оценка:</h6>
                        <div className="ant107_shop-ratings"
                             id="ant107_shop-your-rating">

                            <Rating activeColor={"#ffd700"} value={evaluation} count={5} size={24}
                                    onChange={ratingChanged}/>
                        </div>
                    </div>
                </div>
                <div className="col-md-4">
                    <div className="ant107_shop-upload-btn-wrapper">
                        <button className="ant107_shop-upload-btn">
                            <span><i className="fas fa-file-image"></i>Вложения</span>
                        </button>
                        <input type="file" name="myfile" onChange={handleFileChange} multiple={true}/>
                    </div>
                    {images.length > 0 && (
                        <div>
                            <span className="mb-0 mr-2 ">Загруженные файлы:</span>
                            {images.map((image, index) => (
                                <img key={index} src={image} alt={`Uploaded ${index + 1}`} style={{ maxWidth: '10%' }} />
                            ))}
                        </div>
                    )}
                </div>
                <div className="col-md-4">
                    <div className="form-group text-left text-md-right mb-0">
                        <button className="ant107_shop-theme-btn ant107_shop-br-30"
                                // type="submit"
                                onClick={()=>addFeedback(props.idProduct)}
                        >Отправить
                        </button>
                    </div>
                </div>
            </div>
        </form>

    }

export default FormForDetailProduct;