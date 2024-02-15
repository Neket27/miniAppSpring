import React, {createContext} from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import Store from "./auth/store/Store";
import { BrowserRouter } from 'react-router-dom';
import CardProductController from "./product/controller/CardProductController";
import CartController from "./content/cart/controller/CartController";


const store =new Store();
// const productCardController = new CardProductController();

interface State {
    store:Store
    updateCountProductInCart: any
    // productCardController:CardProductController
}
const updateCountProductInCart = async () => {
    try {
        const count = await CartController.getCountProductInCart(localStorage.getItem('token'));
        localStorage.setItem('countProductInCart',count);
        console.log("countMAin= "+count)
        return count;
    } catch (error) {
        console.error('Ошибка при обновлении количества товаров в корзине:', error);
        throw error;
    }
};
export const Context = createContext<State>({
    store,
    updateCountProductInCart
    // productCardController
});



ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
      <BrowserRouter>
      <Context.Provider value={{store,updateCountProductInCart}}>
        <App/>
      </Context.Provider>
      </BrowserRouter>
  </React.StrictMode>,
)
