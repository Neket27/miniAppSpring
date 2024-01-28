import React, {useEffect, useState} from "react";
import ProductService from "../../product/service/productService";
import {CategoryResponse} from "../../product/model/response/CategoryResponse";
import {transliterate} from "transliteration";

const Category = ()=>{
    const [categoryMap,setCategoryMap]=useState<Map<string,number>>(new Map());

    async function getMapCategory(){
        const response = ProductService.getMapCategory();
        const jsonCategoryResponse=await response.then(r => r);

        setCategoryMap(jsonToMap(jsonCategoryResponse));

    }

    function jsonToMap(json:CategoryResponse): Map<string, number> {
        const map = new Map<string, number>();

        for (const [key, value] of Object.entries(json.numberOfProductsInThisCategory)) {
            map.set(key, value);
        }
        return map;
    }

useEffect(()=>{
    getMapCategory();
},[]);

    let categoryJsx:any[]=[];

    categoryMap.forEach((count, category) =>
      categoryJsx.push(
        <li key={transliterate(category)}><a href="#">
            <i className="fas fa-chevron-right mr-2"></i> {category} <span>({count})</span></a>
        </li>)
    );

    return (
        <aside className="col-xl-3 col-md-4">
            <div className="ant107_shop-shop-sidebar">
            <div className="ant107_shop-shop-widget">
                    <div className="ant107_shop-shop-widget-title">
                        <h3>Категории</h3>
                    </div>
                <ul>
                    {categoryJsx}
                </ul>
            </div>
                <div className="ant107_shop-shop-widget">
                    <form className="ant107_shop-search d-block">
                        <span className="fas fa-search"></span>
                        <input type="search" placeholder="Поиск"/>
                    </form>
                </div>
            </div>
        </aside>
        );
}


export default Category;