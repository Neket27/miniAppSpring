import React, {createContext} from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import {BrowserRouter} from 'react-router-dom';
import AuthService from "./service/auth/AuthService";
import ProductService from "./service/product/ProductService";
import BagService from "./service/bag/BagService";

export interface State {
    authService:AuthService;
    productService:ProductService;
    bagService:BagService;
}

const authService:AuthService = new AuthService();
const productService:ProductService = new ProductService();
const bagService:BagService = new BagService();

export const ContextService:React.Context<State> = createContext<State>({
    authService,
    productService,
    bagService,
});


ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
      <BrowserRouter>
      <ContextService.Provider value={{authService,productService,bagService}}>
        <App/>
      </ContextService.Provider>
      </BrowserRouter>
  </React.StrictMode>,
)
