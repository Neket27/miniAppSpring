import {useContext, useEffect, useState} from "react";
import {ContextService} from "../../../../main";
import {IImage} from "../../../../model/product/IImage";
import {File} from "node:buffer";
import {IAddProduct} from "../../../../model/product/IAddProduct";
import {IDetailProduct} from "../../../../model/product/IDetailProduct";
import {useLocation} from "react-router-dom";
import {IUpdateProduct} from "../../../../model/product/IUpdateProduct";

export const UpdateProduct =()=>{

    const context = useContext(ContextService)
    const location = useLocation();
    const product = location.state?.product;
    const [category,setCategory] = useState('');
    const [subcategory,setSubcategory] = useState('');
    const [brand,setBrand] = useState('');
    const [name, setName] = useState('');
    const [cost,setCost] = useState('');
    const [description,setDescription] = useState('');
    const [article,setArticle] = useState('');
    const [available,setAvailable] = useState(false);
    const [stock,setStock] = useState('');
    const [detail,setDetail] = useState('');
    const [producerCountry,setProducerCountry] = useState('');
    const [sellerWarranty,setSellerWarranty] = useState('');
    const [images,setImages] = useState(new Array<IImage>());
    const [selectedImageIndex, setSelectedImageIndex] = useState<number | null>(null);

    // const getProduct = async () => {
    //     context.productService.getProductDetail()
    // }

    useEffect(() => {
        // console.log(product)
        // if(product!=undefined) {
            setCategory(product.category || '');
            setSubcategory(product.subcategory || '');
            setBrand(product.brand || '');
            setName(product.name || '');
            setCost(product.cost?.toString() || '');
            setDescription(product.description || '');
            setArticle(product.article || '');
            setAvailable(product.available || false);
            setStock(product.stock?.toString() || '');
            setDetail(product.detail || '');
            setProducerCountry(product.characteristicProductDto.producerCountry || '');
            setSellerWarranty(product.characteristicProductDto.sellerWarranty?.toString() || '');
            setImages(product.imageDtoList || []);
        // }
    }, []);

    const handleFileChange = (f:File[]) => {
        const files:File[] = Array.from(f); // Преобразование FileList в массив
        const promises = files.map((file:File) => {
            return new Promise((resolve, reject) => {
                const reader = new FileReader();
                reader.onload = () => {
                    const base64String = String(reader.result).split(',')[1];
                    const image :IImage={
                        name:file.name,
                        contentType:file.type,
                        base64:base64String
                    }
                    resolve(image);}
                reader.onerror = (error) => reject(error);
                reader.readAsDataURL(file as Blob); // Чтение файла как Data URL (Base64)
            });
        });

        Promise.all(promises)
            .then((base64Images:IImage[]) => {
                // images.push(base64Images)
                setImages([...images, ...base64Images]);
                // Сохранение Base64 строк в состоянии
            })
            .catch((error) => {
                console.error('Ошибка при чтении файлов:', error);
            });
    };


    async function handleFileUpload(e: React.MouseEvent<HTMLButtonElement>) {
        e.preventDefault(); // предотвратить стандартное поведение формы

        const p: IUpdateProduct = {
            id: product.id,
            category: category, // Используем текущее значение состояния
            subcategory: subcategory,
            name: name,
            cost: parseFloat(cost), // Преобразуем строку в число
            rating: 0, // Можно изменить, если потребуется
            description: description,
            brand: brand,
            article: article,
            available: available,
            stock: parseInt(stock), // Преобразуем строку в число
            detail: detail,
            producerCountry: producerCountry,
            sellerWarranty: parseInt(sellerWarranty), // Преобразуем строку в число
            updateImageDtoList: images, // Используем текущее значение массива изображений
        };

        context.productService.updateProduct(p)
    }

    const handleDeleteImage = () => {
        if (selectedImageIndex !== null) {
            const updatedImages = [...images];
            updatedImages.splice(selectedImageIndex, 1);
            setImages(updatedImages);
            setSelectedImageIndex(null); // Сбросить выбор
        }
    };

    const handleImageClick = (index: number) => {
        setSelectedImageIndex(index);
    };


    return (<div className="limiter">
            <div className="container-login100">
                <div className="wrap-login100">
                    <div className="login100-pic js-tilt" data-tilt>
                        {images.map((image, index) => (
                            <div key={index} style={{ position: 'relative', display: 'inline-block' }}>
                                <img
                                    src={"data:image/png;base64," + image.base64}
                                    alt={`Загружено ${index}`}
                                    style={{ width: '100px', height: '100px', objectFit: 'cover', cursor: 'pointer' }}
                                    onClick={() => handleImageClick(index)}
                                />
                                {selectedImageIndex === index && (
                                    <button
                                        onClick={handleDeleteImage}
                                        style={{
                                            position: 'absolute',
                                            top: '0',
                                            right: '0',
                                            backgroundColor: 'red',
                                            color: 'white',
                                            border: 'none',
                                            cursor: 'pointer'
                                        }}
                                    >
                                        Удалить
                                    </button>
                                )}
                            </div>
                        ))}
                    </div>

                    <div className="login100-form validate-form">
                        <form>
					<span className="login100-form-title">
						Добавление продукта
					</span>
                            <div className="container mt-4">
                                <div className="card p-4">
                                    <h2 className="mb-3">Загрузка изображений</h2>
                                    {/*<div className="form-group">*/}
                                    <label className="btn btn-primary ">
                                        Загрузить изображения
                                        <input
                                            type="file"
                                            multiple
                                            onChange={(e) => handleFileChange(e.target.files)}
                                            className="d-none"
                                            name="uploadedFiles"
                                        />
                                    </label>
                                    {/*</div>*/}
                                    {/*<button*/}
                                    {/*    onClick={handleFileChange}*/}
                                    {/*    disabled={uploading}*/}
                                    {/*   */}
                                    {/*>*/}
                                    {/*    {uploading ? 'Загрузка...' : 'Загрузить картинки'}*/}
                                    {/*</button>*/}
                                </div>
                            </div>


                            <div className="wrap-input100 validate-input"
                                 data-validate="Valid email is required: ex@abc.xyz">
                                <input className="input100"
                                       onChange={e => setCategory(e.target.value)}
                                       value={category}
                                       type="text"
                                       placeholder="Категория"
                                       required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
							<i className="fa fa-envelope" aria-hidden="true"></i>
						</span>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Password is required">
                                <input className="input100"
                                       onChange={e => setSubcategory(e.target.value)}
                                       value={subcategory}
                                       type="text"
                                       placeholder="Подкатегория"
                                       required={false}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
							<i className="fa fa-lock" aria-hidden="true"></i>
						</span>
                            </div>

                            <div className="wrap-input100 validate-input"
                                 data-validate="Valid email is required: ex@abc.xyz">
                                <input className="input100"
                                       onChange={e => setBrand(e.target.value)}
                                       value={brand}
                                       type="text"
                                       placeholder="Бренд"
                                       required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
							<i className="fa fa-envelope" aria-hidden="true"></i>
						</span>
                            </div>

                            <div className="wrap-input100 validate-input"
                                 data-validate="Valid email is required: ex@abc.xyz">
                                <input className="input100"
                                       onChange={e => setName(e.target.value)}
                                       value={name}
                                       type="text"
                                       placeholder="Название"
                                       required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
							<i className="fa fa-envelope" aria-hidden="true"></i>
						</span>
                            </div>

                            <div className="wrap-input100 validate-input"
                                 data-validate="Valid email is required: ex@abc.xyz">
                                <input className="input100"
                                       onChange={e => setCost(e.target.value)}
                                       value={cost}
                                       type="text"
                                       placeholder="Стоимость"
                                       required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
							<i className="fa fa-envelope" aria-hidden="true"></i>
						</span>
                            </div>

                            <div className="wrap-input100 validate-input"
                                 data-validate="Valid email is required: ex@abc.xyz">
                                <input className="input100"
                                       onChange={e => setDescription(e.target.value)}
                                       value={description}
                                       type="text"
                                       placeholder="Описание"
                                       required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
							<i className="fa fa-envelope" aria-hidden="true"></i>
						</span>
                            </div>

                            <div className="wrap-input100 validate-input"
                                 data-validate="Valid email is required: ex@abc.xyz">
                                <input className="input100"
                                       onChange={e => setArticle(e.target.value)}
                                       value={article}
                                       type="text"
                                       placeholder="Заметка"
                                       required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
							<i className="fa fa-envelope" aria-hidden="true"></i>
						</span>
                            </div>

                            <div className="wrap-input100 validate-input"
                                 data-validate="Valid email is required: ex@abc.xyz">
                                <input className="input100"
                                       onChange={e => setAvailable(e.target.value == 'Да')}
                                       defaultValue={available == true ? 'ДА' : 'Нет'}
                                       type="text"
                                       placeholder="Показавать товар в магазине"
                                       required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
							<i className="fa fa-envelope" aria-hidden="true"></i>
						</span>
                            </div>

                            <div className="wrap-input100 validate-input"
                                 data-validate="Valid email is required: ex@abc.xyz">
                                <input className="input100"
                                       onChange={e => setStock(e.target.value)}
                                       value={stock}
                                       type="text"
                                       placeholder="Количество на складе"
                                       required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
							<i className="fa fa-envelope" aria-hidden="true"></i>
						</span>
                            </div>

                            <div className="wrap-input100 validate-input"
                                 data-validate="Valid email is required: ex@abc.xyz">
                                <input className="input100"
                                       onChange={e => setDetail(e.target.value)}
                                       value={detail}
                                       type="text"
                                       placeholder="Детали продукта"
                                       required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
							<i className="fa fa-envelope" aria-hidden="true"></i>
						</span>
                            </div>

                            <div className="wrap-input100 validate-input"
                                 data-validate="Valid email is required: ex@abc.xyz">
                                <input className="input100"
                                       onChange={e => setProducerCountry(e.target.value)}
                                       value={producerCountry}
                                       type="text"
                                       placeholder="Страна производитель"
                                       required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
							<i className="fa fa-envelope" aria-hidden="true"></i>
						</span>
                            </div>

                            <div className="wrap-input100 validate-input"
                                 data-validate="Valid email is required: ex@abc.xyz">
                                <input className="input100"
                                       onChange={e => setSellerWarranty(e.target.value)}
                                       value={sellerWarranty}
                                       type="text"
                                       placeholder="Гарантия от производителя"
                                       required={true}/>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
							<i className="fa fa-envelope" aria-hidden="true"></i>
						</span>
                            </div>


                            <button className="login100-form-btn"
                                    onClick={(e) => {
                                        e.preventDefault(); // Отмена действия по умолчанию
                                        handleFileUpload(e);
                                    }}>
                                Сохранить
                            </button>

                        </form>

                    </div>
                </div>
            </div>
        </div>

    );

}