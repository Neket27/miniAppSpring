import React, { useState } from "react";
import { ICardProduct } from "../../../model/product/ICardProduct";
import Product from "../../../home/product";

interface ProductPageTodoProps {
    products?: Array<ICardProduct>; // Сделаем необязательным
}

export const ProductPageTodo = ({ products = [] }: ProductPageTodoProps) => {
    const [currentPage, setCurrentPage] = useState(1); // Текущая страница
    const itemsPerPage = 9; // Количество элементов на одной странице

    const totalPages = Math.ceil(products.length / itemsPerPage);

    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = products.slice(indexOfFirstItem, indexOfLastItem);

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
                {currentItems.length > 0 ? (
                    <Product products={currentItems} />
                ) : (
                    <p>Товары не найдены</p>
                )}
            </div>

            {totalPages > 1 && (
                <div>
                    <nav className="ant107_shop-pazination mt-4">
                        <ul>
                            {[...Array(totalPages)].map((_, index: number) => (
                                <li
                                    key={index}
                                    onClick={() => goToPage(index + 1)}
                                    className={currentPage === index + 1 ? "active" : ""}
                                >
                                    <a href="#">{index + 1}</a>
                                </li>
                            ))}
                        </ul>
                    </nav>
                    <p style={{ paddingTop: "10px" }}>Страница {currentPage} из {totalPages}</p>
                </div>
            )}
        </div>
    );
};
