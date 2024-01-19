import React, {createContext} from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import './index.css'
import Store from "./auth/store/Store";
import { BrowserRouter } from 'react-router-dom';


const store =new Store();

interface State {
    store:Store
}

export const Context = createContext<State>({
    store
});

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
      <BrowserRouter>
      <Context.Provider value={{store}}>
        <App/>
      </Context.Provider>
      </BrowserRouter>
  </React.StrictMode>,
)
