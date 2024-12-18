import {useContext} from "react";
import {ContextService} from "../../../../main";
import {useLocation} from "react-router-dom";

export const DeleteProduct = () =>{
    const context = useContext(ContextService)
    const location = useLocation();
    const productId = location.state?.productId;

    const deleteProduct = async () => {
        await context.productService.deleteProduct(productId);
    }

    return (
        <div>
            <button onClick={()=>deleteProduct()}>Удалить</button>
        </div>
    );
}