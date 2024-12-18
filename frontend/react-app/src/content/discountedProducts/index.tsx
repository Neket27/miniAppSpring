import React, { useEffect, useState, useContext } from 'react';
import './../../../css/style.css';
import './../../../css/bootstrap.min.css';
import './../../../css/magnify.css';
import './../../../css/ant107_shop.css';
import { ICardProduct } from "../../model/product/ICardProduct";
import ProductCard from "../detail/product/ProductCard";
import { ContextService } from "../../main";

const DiscountedProducts = () => {
    const [discountedProducts, setDiscountedProducts] = useState<ICardProduct[]>([]);
    const context = useContext(ContextService);

    async function fetchDiscountedProducts() {
        // Предполагается, что сервис возвращает только товары со скидками
        const productsWithDiscounts = await context.productService.getDiscountedProducts();
        setDiscountedProducts(productsWithDiscounts);
    }

    useEffect(() => {
        fetchDiscountedProducts();
    }, []);

    return (
        <div id="ant107_shop" className="ant107_shop_container">
            <div className="container">
                <h2 className="mb-4">Товары со скидками</h2>
                <div className="product-grid">
                    {discountedProducts.map(product => (
                        <ProductCard key={product.id} product={product} />
                    ))}
                </div>
            </div>
        </div>
    );
}

export default DiscountedProducts;
