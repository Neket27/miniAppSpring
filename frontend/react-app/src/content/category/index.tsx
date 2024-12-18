import React, {useContext, useEffect, useState} from "react";
import ProductController from "../../controller/ProductController";
import {CategoryResponse} from "../../model/response/product/CategoryResponse";
import {transliterate} from "transliteration";
import {Link} from "react-router-dom";
import {SearchResponse} from "../../model/response/product/SearchResponse";
import {ContextService} from "../../main";

const Category = (props: { OnClickGetListProduct(category:string): void; })=>{
    const contextService = useContext(ContextService)
    const [categoryMap,setCategoryMap]=useState<Map<string,number>>(new Map());
    const [searchString, setSearchString] = useState('');
    const [searchedProducts, setSearchedProducts] = useState<SearchResponse[]>([]);

    async function getMapCategory(){
        const response = contextService.categoryService.getMapCategory();
        const jsonCategoryResponse=await response.then(r => r);
        setCategoryMap(jsonToMap(jsonCategoryResponse));
    }

    async function search(searchValue:string){
        const response = await ProductController.search(searchValue);
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

    async function  operation(searchText:string){
        setSearchedProducts([]);
        setSearchString('');
        categoryJsx=[];
        props.OnClickGetListProduct(searchText)
    }

    useEffect(()=>{
        getMapCategory();
        // props.OnClickGetListProduct(ct);
    },[]);


    let categoryJsx:any[]=[];
    const searchCategoryJsx = searchedProducts.map((searchedResponse,index)=> {
        const categoryOnEnglish: string = transliterate(searchedResponse.category);
        if (searchedResponse.idProduct ==null) {
            return <li key={index}>
                <Link to={"/detailProduct/" + searchedResponse.idProduct} onClick={() => {
                    operation(searchedResponse.nameProduct);
                }}>
                    <i className="fas fa-chevron-right mr-2"></i> {searchedResponse.nameProduct} <span></span>
                </Link>
            </li>
        }
        return <li key={index}>
            <Link to={"/detailProduct/" + searchedResponse.idProduct} onClick={() => {
               operation(searchedResponse.nameProduct);
            }}>
                <i className="fas fa-chevron-right mr-2"></i> {searchedResponse.nameProduct} <span></span>
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
                    <li key={"allCategories"} onClick={() => {
                        operation("Все категории");
                    }}>
                        <Link to={"/category/product/" + "allCategories"}>
                            <i className="fas fa-chevron-right mr-2"></i> Все категории <span></span>
                        </Link>
                    </li>

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