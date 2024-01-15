import App from './app';
import React from "react";
import ReactDOM from "react-dom/client";


//
// ReactDOM.render(<App />, document.getElementById("root"));
//
// const devMode = process.env.NODE_ENV === 'development';
// if (devMode && module && module.hot) {
//     module.hot.accept();
// }

const root = ReactDOM.createRoot(document.getElementById("root") as HTMLElement);

root.render(
    <React.StrictMode>
        <App />
    </React.StrictMode>
);