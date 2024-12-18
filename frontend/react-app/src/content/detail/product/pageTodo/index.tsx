import React, { useState } from "react";
import { ICardProduct } from "../../../model/product/ICardProduct";
import Product from "../../../home/product";

interface ProductPageTodoProps {
    products: Array<ICardProduct>;
}

export const ProductPageTodo = (props: ProductPageTodoProps) => {
    const [currentPage, setCurrentPage] = useState(1); // Текущая страница
    const itemsPerPage = 9; // Количество элементов на одной странице

    // Определение количества страниц
    const totalPages = Math.ceil(props.products.length / itemsPerPage);

    // Разбиение элементов на страницы
    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = props.products.slice(indexOfFirstItem, indexOfLastItem);

    // Функции для переключения между страницами
    const goToPage = (page: number) => {
        setCurrentPage(page);
    };

    const goToNextPage = () => {
        setCurrentPage((prevPage) => Math.min(prevPage + 1, totalPages));
    };

    const goToPreviousPage = () => {
        setCurrentPage((prevPage) => Math.max(prevPage - 1, 1));
    };

    return (
        <div>
            <div className="row">
                <Product products={currentItems}/>
            </div>

            <div>
                <nav className="ant107_shop-pazination mt-4">
                    <ul>
                        {[...Array(totalPages)].map((_, index: number) => (
                            <li
                                key={index}
                                onClick={() => goToPage(index + 1)}
                                // currentPage=== index + 1 ? ' className="active"' : ''
                            >
                                <a href="#">{index + 1}</a>
                            </li>
                        ))}
                    </ul>
                </nav>
            </div>

            <p style={{paddingTop: '10px'}}>Страница {currentPage} из {totalPages}</p>
        </div>
    )
        ;
};
