import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import LoginPage from "./auth/login";
import Home from "./auth/content/home";

function App() {
    return (
        <div className="App">
            <Home></Home>
            <LoginPage/>
        </div>
    );

};

export default App
