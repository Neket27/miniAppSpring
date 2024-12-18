import {useContext, useEffect, useState} from "react";
import {ContextService} from "../../../../main";
import {IImage} from "../../../../model/product/IImage";
import {File} from "node:buffer";
import {useLocation} from "react-router-dom";
import {IUpdateProduct} from "../../../../model/product/IUpdateProduct";
import ProductController from "../../../../controller/ProductController";
import {CategoryResponse} from "../../../../model/response/product/CategoryResponse";

export const UpdateProduct =()=>{

    const context = useContext(ContextService)
    const location = useLocation();
    const product = location.state?.product;
    const [category,setCategory] = useState('');
    // const [subcategory,setSubcategory] = useState('');
    const [brand,setBrand] = useState('');
    const [name, setName] = useState('');
    const [cost,setCost] = useState('');
    const [description,setDescription] = useState('');
    const [note,setNote] = useState('');
    const [available,setAvailable] = useState(false);
    const [stock,setStock] = useState('');
    const [detail,setDetail] = useState('');
    const [producerCountry,setProducerCountry] = useState('');
    const [sellerWarranty,setSellerWarranty] = useState('');
    const [images,setImages] = useState(new Array<IImage>());
    const [selectedImageIndex, setSelectedImageIndex] = useState<number | null>(null);
    const [categoryMap,setCategoryMap]=useState<Map<string,number>>(new Map());

    useEffect(() => {
            setCategory(product.category || '');
            // setSubcategory(product.subcategory || '');
            setBrand(product.brand || '');
            setName(product.name || '');
            setCost(product.cost?.toString() || '');
            setDescription(product.description || '');
            setNote(product.note || '');
            setAvailable(product.available || false);
            setStock(product.stock?.toString() || '');
            setDetail(product.detail || '');
            setProducerCountry(product.characteristicProductDto.producerCountry || '');
            setSellerWarranty(product.characteristicProductDto.sellerWarranty?.toString() || '');
            setImages(product.imageDtoList || []);
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


    async function getMapCategory(){
        const response = context.categoryService.getMapCategory();
        const jsonCategoryResponse=await response.then(r => r);
        setCategoryMap(jsonToMap(jsonCategoryResponse));

    }


    function jsonToMap(json:CategoryResponse): Map<string, number> {
        const map = new Map<string, number>();

        for (const [key, value] of Object.entries(json.numberOfProductsInThisCategory)) {
            map.set(key, value);
        }
        // console.log(map);
        return map;
    }


    async function handleFileUpload(e: React.MouseEvent<HTMLButtonElement>) {
        e.preventDefault(); // предотвратить стандартное поведение формы

        const p: IUpdateProduct = {
            id: product.id,
            category: category, // Используем текущее значение состояния
            // subcategory: subcategory,
            name: name,
            cost: parseFloat(cost), // Преобразуем строку в число
            rating: 0, // Можно изменить, если потребуется
            description: description,
            brand: brand,
            note: note,
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

    useEffect(()=>{
        getMapCategory();
    },[categoryMap.size>0])



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
                        <h3 className="text-center mb-0">
                            Редактирование продукта
                        </h3>
                        <form>
                            <div className="container mt-4 mb-4">
                                <div className="card p-4">
                                    <h4 className="mb-3">Загрузка изображений</h4>
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
                                </div>
                            </div>

                            {/*                        <div className="wrap-input100 validate-input">*/}
                            {/*                            <input*/}
                            {/*                                className="input100"*/}
                            {/*                                onChange={e => setSubcategory(e.target.value)}*/}
                            {/*                                value={subcategory}*/}
                            {/*                                type="text"*/}
                            {/*                                placeholder="Подкатегория"*/}
                            {/*                                required={false}*/}
                            {/*                            />*/}
                            {/*                            <span className="focus-input100"></span>*/}
                            {/*                            <span className="symbol-input100">*/}
                            {/*    <i className="fa fa-sitemap" aria-hidden="true"></i> /!* Иконка для подкатегории *!/*/}
                            {/*</span>*/}
                            {/*                        </div>*/}

                            <div className="wrap-input100 validate-input">
                                <select className="input100" value={category}
                                        onChange={(e) => setCategory(e.target.value)}
                                        required>
                                    <option value="">Выберите категорию</option>
                                    {Array.from(categoryMap).map(([category, count], index) => (
                                        <option key={index} value={category}>
                                            {category}
                                        </option>
                                    ))}
                                </select>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                                {/*<i className="fa fa-envelope" aria-hidden="true"></i>*/}
                            </span>
                            </div>

                            <div className="wrap-input100 validate-input">
                                <input
                                    className="input100"
                                    onChange={e => setBrand(e.target.value)}
                                    value={brand}
                                    type="text"
                                    placeholder="Бренд"
                                    required={true}
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
        <i className="fa fa-flag" aria-hidden="true"></i> {/* Иконка для бренда */}
    </span>
                            </div>

                            <div className="wrap-input100 validate-input">
                                <input
                                    className="input100"
                                    onChange={e => setName(e.target.value)}
                                    value={name}
                                    type="text"
                                    placeholder="Название"
                                    required={true}
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
        <i className="fa fa-tag" aria-hidden="true"></i> {/* Иконка для названия */}
    </span>
                            </div>

                            <div className="wrap-input100 validate-input">
                                <input
                                    className="input100"
                                    onChange={e => setCost(e.target.value)}
                                    value={cost}
                                    type="text"
                                    placeholder="Стоимость"
                                    required={true}
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
        <i className="fa fa-dollar" aria-hidden="true"></i> {/* Иконка для стоимости */}
    </span>
                            </div>

                            <div className="wrap-input100 validate-input">
                                <input
                                    className="input100"
                                    onChange={e => setDescription(e.target.value)}
                                    value={description}
                                    type="text"
                                    placeholder="Описание"
                                    required={true}
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
        <i className="fa fa-info-circle" aria-hidden="true"></i> {/* Иконка для описания */}
    </span>
                            </div>

                            <div className="wrap-input100 validate-input">
                                <input
                                    className="input100"
                                    onChange={e => setNote(e.target.value)}
                                    value={note}
                                    type="text"
                                    placeholder="Заметка"
                                    required={true}
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
        <i className="fa fa-sticky-note" aria-hidden="true"></i> {/* Иконка для заметки */}
    </span>
                            </div>

                            <div className="wrap-input100 validate-input">
                                <input
                                    className="input100"
                                    onChange={e => setStock(e.target.value)}
                                    value={stock}
                                    type="text"
                                    placeholder="Количество на складе"
                                    required={true}
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
        <i className="fa fa-archive" aria-hidden="true"></i> {/* Иконка для склада */}
    </span>
                            </div>

                            <div className="wrap-input100 validate-input">
                                <input
                                    className="input100"
                                    onChange={e => setDetail(e.target.value)}
                                    value={detail}
                                    type="text"
                                    placeholder="Детали продукта"
                                    required={true}
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
        <i className="fa fa-cogs" aria-hidden="true"></i> {/* Иконка для деталей */}
    </span>
                            </div>

                            <div className="wrap-input100 validate-input">
                                <input
                                    className="input100"
                                    onChange={e => setProducerCountry(e.target.value)}
                                    value={producerCountry}
                                    type="text"
                                    placeholder="Страна производитель"
                                    required={true}
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
        <i className="fa fa-globe" aria-hidden="true"></i> {/* Иконка для страны */}
    </span>
                            </div>

                            <div className="wrap-input100 validate-input">
                                <input
                                    className="input100"
                                    onChange={e => setSellerWarranty(e.target.value)}
                                    value={sellerWarranty}
                                    type="text"
                                    placeholder="Гарантия от производителя"
                                    required={true}
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
        <i className="fa fa-check-circle" aria-hidden="true"></i> {/* Иконка для гарантии */}
    </span>
                            </div>


                            <div className="wrap-input100 validate-input"
                                 onClick={() => setAvailable(prevState => !prevState)}>
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                                <i>Показавать товар в магазине</i>
						    </span>
                                <input
                                    type="checkbox"
                                    checked={available}
                                    onChange={() => setAvailable(prevState => !prevState)}
                                    placeholder="Показавать товар в магазине"
                                    required={true}/>
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