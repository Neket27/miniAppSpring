const imageProduct=(prods:{base64:string|undefined})=> {
    if(prods.base64!==undefined)
        return (
            <li>
                <a data-toggle="tab" href="#ant107_shop-preview1">
                    <img src={"data:image/png;base64," + prods.base64} alt=""/>
                </a>
            </li>
        );
    else
        return <div></div>
}

export default imageProduct;