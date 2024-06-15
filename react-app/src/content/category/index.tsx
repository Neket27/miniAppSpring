import React, {useEffect, useState} from "react";
import ProductService from "../../product/service/productService";
import {CategoryResponse} from "../../product/model/response/CategoryResponse";
import {transliterate} from "transliteration";
import {Link} from "react-router-dom";
import {ICategory} from "../../product/model/ICategory";
import {CategorySearchResponse} from "../../product/model/response/CategorySearchResponse";

const Category = (props: { OnClickGetListProduct(category:ICategory): void; })=>{
    const [categoryMap,setCategoryMap]=useState<Map<string,number>>(new Map());
    const [searchString, setSearchString] = useState('');
    const [searchedProducts, setSearchedProducts] = useState<CategorySearchResponse[]>([]);

    async function getMapCategory(){
        const response = ProductService.getMapCategory();
        const jsonCategoryResponse=await response.then(r => r);
        setCategoryMap(jsonToMap(jsonCategoryResponse));
    }

    async function search(searchValue:string){
        const response = await ProductService.search(searchValue);
        // console.log(response)
        setSearchedProducts([]);
        setSearchedProducts(response);
    }

    function jsonToMap(json:CategoryResponse): Map<string, number> {
        const map = new Map<string, number>();

        for (const [key, value] of Object.entries(json.numberOfProductsInThisCategory)) {
            map.set(key, value);
        }
        return map;
    }

function operation(category:string):void{
    let ct:ICategory = {
        categoryProduct: category,
        subcategory: "not_supported",
        stringValueCategory: " stringValueCategory"
    }
    setSearchedProducts([]);
    setSearchString('');
    categoryJsx=[];
    props.OnClickGetListProduct(ct)
}

useEffect(()=>{
    getMapCategory();
    // props.OnClickGetListProduct(ct);
},[]);


    let categoryJsx:any[]=[];
    const searchCategoryJsx = searchedProducts.map((category,index)=> {
        const categoryOnEnglish: string = transliterate(category.stringValueCategory);
        if (category.idProduct ==null) {
            return <li key={index}>
                <Link to={"/category/product/" + categoryOnEnglish} onClick={() => {
                    operation(category.stringValueCategory);
                }}>
                    <i className="fas fa-chevron-right mr-2"></i> {category.stringValueCategory} <span></span>
                </Link>
            </li>
        }
        return <li key={index}>
            <Link to={"/category/product/" + categoryOnEnglish} onClick={() => {
               operation(category.stringValueCategory);
            }}>
                <i className="fas fa-chevron-right mr-2"></i> {category.nameProduct} <span></span>
            </Link>
              </li>
    });

    categoryMap.forEach((count, category) => {
        if (count != 0) {
            const categoryOnEnglish: string = transliterate(category);
            categoryJsx.push(
                <li key={categoryOnEnglish} onClick={() => {
                   operation(category);
                }}>
                    <Link to={"/category/product/" + categoryOnEnglish}>
                        <i className="fas fa-chevron-right mr-2"></i> {category} <span>({count})</span>
                    </Link>
                </li>)
        }
    });


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
                        <input type="search"
                               placeholder="Поиск"
                               onChange={e => {
                                   setSearchString(e.target.value);
                                   if(searchString.length % 2==0) {
                                       setSearchedProducts([]);
                                       categoryJsx=[];
                                       search(searchString);

                                   }else if(searchString.length==1) {
                                       setSearchedProducts([]);
                                       categoryJsx=[];
                                   }

                               }
                               }
                               value={searchString}/>
                    </form>
                    <br/>
                    <ul>
                        {searchCategoryJsx}
                    </ul>
                </div>
            </div>
        </aside>
    );
}


export default Category;