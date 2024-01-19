
import React from "react";
import ReactDOM from "react-dom/client";

import App from "./App";

// import Store from "./auth/store/Store";
//
// // @ts-ignore
// const store =new Store();
//
// interface State {
//     store:Store
// }
//
// export const Context = createContext<State>({
//     store
// });

const  root =ReactDOM.createRoot(document.getElementById('root')as HTMLElement);

root.render(
    <React.StrictMode>
        {/*<BrowserRouter>*/}
        {/*<Context.Provider value={{store}}>*/}
             <App/>
        {/*</Context.Provider>*/}
        {/*</BrowserRouter>*/}
    </React.StrictMode>

);

