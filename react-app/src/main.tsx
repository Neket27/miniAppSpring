import React, {createContext} from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import Store from "./auth/store/Store";
import { BrowserRouter } from 'react-router-dom';
import CardProductController from "./product/controller/CardProductController";


const store =new Store();
const productCardController = new CardProductController();

interface State {
    store:Store
    productCardController:CardProductController
}

export const Context = createContext<State>({
    store,
    productCardController
});

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
      <BrowserRouter>
      <Context.Provider value={{store,productCardController}}>
        <App/>
      </Context.Provider>
      </BrowserRouter>
  </React.StrictMode>,
)
