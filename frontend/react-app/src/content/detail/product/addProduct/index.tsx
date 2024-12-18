import {FC, useContext, useEffect, useState} from "react";
import {IAddProduct} from "../../../../model/product/IAddProduct";
import {ContextService} from "../../../../main";
import {IImage} from "../../../../model/product/IImage";
import {File} from "node:buffer";
import ProductController from "../../../../controller/ProductController";
import {CategoryResponse} from "../../../../model/response/product/CategoryResponse";

export const ProductAdd:FC = ()=>{
    const context = useContext(ContextService)
    const [category,setCategory] = useState('');
    const [subcategory,setSubcategory] = useState('');
    const [categoryMap,setCategoryMap]=useState<Map<string,number>>(new Map());
    const [brand,setBrand] = useState('');
    const [name, setName] = useState('');
    const [cost,setCost] = useState('');
    const [description,setDescription] = useState('');
    const [note,setNote] = useState('');
    const [available,setAvailable] = useState(true);
    const [stock,setStock] = useState('');
    const [detail,setDetail] = useState('');
    const [producerCountry,setProducerCountry] = useState('');
    const [sellerWarranty,setSellerWarranty] = useState('');
    const [images,setImages] = useState(new Array<IImage>());

    useEffect(()=>{
        getMapCategory();
    },[categoryMap.size>0])

    async function getMapCategory(){
        const jsonCategoryResponse = await context.categoryService.getMapCategory().then(r => r);
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


    async function handleFileUpload() {

            const product: IAddProduct = {
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
                createImageDtoList: images, // Используем текущее значение массива изображений
            };
            console.log(product);
            context.productService.addProduct(product)
    }


    return (<div className="limiter">
            <div className="container-login100">
            <div className="wrap-login100">
                <div className="login100-pic js-tilt" data-tilt>
                    {/*<img src="/src/login/images/img-01.png" alt="IMG"/>*/}
                    {images.map((image, index) => (
                        <img
                            key={index}
                            src={"data:image/png;base64,"+image.base64}
                            alt={`Загружено ${index}`}
                            style={{ width: '100px', height: '100px', objectFit: 'cover' }}
                        />
                    ))}
                </div>

                <div className="login100-form validate-form">
                    <h3 className="text-center mb-0">
                        Добавление продукта
                    </h3>
                    <form>
                        <div className="container mt-4 mb-4">
                            <div className="card p-4">
                                <h4 className="mb-3">Загрузка изображений</h4>
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
                            </div>
                        </div>

                        <div className="wrap-input100 validate-input">
                            <select className="input100" value={category} onChange={(e) => setCategory(e.target.value)}
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

    {/*                    <div className="wrap-input100 validate-input">*/}
    {/*                        <input*/}
    {/*                            className="input100"*/}
    {/*                            onChange={e => setSubcategory(e.target.value)}*/}
    {/*                            value={subcategory}*/}
    {/*                            type="text"*/}
    {/*                            placeholder="Подкатегория"*/}
    {/*                            required={false}*/}
    {/*                        />*/}
    {/*                        <span className="focus-input100"></span>*/}
    {/*                        <span className="symbol-input100">*/}
    {/*    <i className="fa fa-sitemap" aria-hidden="true"></i> /!* Иконка для подкатегории *!/*/}
    {/*</span>*/}
    {/*                    </div>*/}

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


                        <div className="container-login100-form-btn">
                            <button className="login100-form-btn"
                                    onClick={() => handleFileUpload()}>
                                Сохранить
                            </button>
                        </div>

                    </form>

                </div>
            </div>
            </div>
        </div>

    );

}