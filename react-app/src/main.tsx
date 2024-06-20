import React, {createContext} from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import {BrowserRouter} from 'react-router-dom';
import AuthService from "./service/auth/AuthService";
import {Client, Message} from "@stomp/stompjs";
import SockJS from "sockjs-client";


export interface State {
    authService:AuthService;
    stompClient:Client;
    // websocketApi:WebsocketApi;
}

const stompClient:Client = new Client();
const authService = new AuthService();
// const websocketApi = new WebsocketApi();


export const Context = createContext<State>({
    authService,
    stompClient
});


ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
      <BrowserRouter>
      <Context.Provider value={{authService,stompClient}}>
        <App/>
      </Context.Provider>
      </BrowserRouter>
  </React.StrictMode>,
)
