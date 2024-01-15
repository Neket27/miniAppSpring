// @ts-ignore
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import React from "react";

import RegisterPage from "./auth/registreition";
import LoginPage from "./auth/login";

const App =()=>{
    return (
            <div className="App">
                <BrowserRouter>
                    <Routes>
                        <Route path='login' element={<LoginPage />}></Route>
                        <Route path='register' element={<RegisterPage />}></Route>
                    </Routes>
                </BrowserRouter>
            </div>


    );
}

export default App;

