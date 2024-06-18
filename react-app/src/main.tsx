import React, {createContext} from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import {BrowserRouter} from 'react-router-dom';
import AuthService from "./service/auth/AuthService";


export interface State {
    authService:AuthService;
    // websocketApi:WebsocketApi;
}

const authService = new AuthService();
// const websocketApi = new WebsocketApi();

export const Context = createContext<State>({
    authService,
    // websocketApi,
});


ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
      <BrowserRouter>
      <Context.Provider value={{authService}}>
        <App/>
      </Context.Provider>
      </BrowserRouter>
  </React.StrictMode>,
)
