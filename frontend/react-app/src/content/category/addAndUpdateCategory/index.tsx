
import { useContext, useEffect, useState } from "react";
import { ContextService } from "../../../main";
import { observer } from "mobx-react-lite";
import '../../../../css/Category.css'

export const AddAndUpdateCategory = observer(() => {
    const { categoryService } = useContext(ContextService);
    const [newCategory, setNewCategory] = useState<string>('');
    const [error, setError] = useState<string | null>(null);
    const [categoryList, setCategoryList] = useState<Array<string>>();

    const getCategoryList = async () => {
        const iCategories = await categoryService.getCategories();
        setCategoryList(iCategories);
    };

    const handleAddCategory = async () => {
        if (categoryList && categoryList.includes(newCategory)) {
            setError('Эта категория уже существует');
            return;
        }
        const  categoryName:string = await categoryService.addCategory(newCategory);

        setNewCategory('');
        setError(null);
        getCategoryList();
    };

    useEffect(() => {
        getCategoryList();
    }, []);

    return (
        <div className="container-change-data-user100">
            <div className="wrap-login100 d-flex flex-column flex-md-row">
                {/* Левая колонка с категориями */}
                <div className="col-md-6 d-flex flex-column align-items-start p-4">
                    <h3 className="login100-form-title">Категории</h3>
                    <ul className="category-list">
                        {categoryList && categoryList.map((category, index) => (
                            <li key={index} className="txt2">{category}</li>
                        ))}
                    </ul>
                </div>

                {/* Правая колонка для добавления новой категории */}
                <div className="col-md-6 d-flex flex-column align-items-start p-4">
                    <h3 className="login100-form-title">Добавить категорию</h3>
                    <div className="wrap-input100">
                        <input
                            className="input100"
                            type="text"
                            placeholder="Новая категория"
                            value={newCategory}
                            onChange={(e) => setNewCategory(e.target.value)}
                        />
                    </div>
                    <button
                        className="login100-form-btn mt-3"
                        onClick={handleAddCategory}
                    >
                        Добавить категорию
                    </button>
                    {error && <p className="txt2 alert-validate" data-validate={error}>{error}</p>}
                </div>
            </div>
        </div>
    );
});
