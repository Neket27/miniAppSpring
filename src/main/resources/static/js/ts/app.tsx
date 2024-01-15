// import * as React from "react";

// import React from "react";
import React from 'react';
import styles from 'styles.scss';


const onClickEvent = (e: { preventDefault: () => void; }) => {
    e.preventDefault();
    alert('You Clicked Me!');
}

// @ts-ignore
const c:string =styles.content;
// @ts-ignore
const l:string =styles.label;
// @ts-ignore
const b:string =styles.btn;
const App = () => {
    return (
        <div className={c}>
            <div className={l}>
                Create React App Without CRA😊
            </div>
            <button className={b} onClick={onClickEvent}>Click Me 😎</button>
        </div>
    )
}

export default App