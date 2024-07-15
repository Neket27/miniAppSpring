import React  from "react";
import LoginPage from "./auth/login";
import Home from "./auth/content/home";


const App =()=>{

    return (
            <div className="App">
                <Home></Home>
                <LoginPage/>
            </div>
    );

};

export default App;
