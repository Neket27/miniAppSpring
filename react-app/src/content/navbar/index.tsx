import React, {useContext, useEffect, useRef, useState} from "react";
import '../../init';
import '../../navbar/style.css';
import '../../navbar/fonts/icomoon/style.css';
import { Link } from "react-router-dom";
import { Context } from "../../main";
import {API_URL} from "../../http";

import  {over} from 'stompjs';
import SockJS from 'sockjs-client';
const URL = import.meta.env.VITE_URL;

var stompClient =null;

const Navbar = () => {
    const [countProductInCart, setCountProductInCart] = useState(0);
    const { updateCountProductInCart } = useContext(Context);
    const webSocket =useRef(0);

    const connect =()=>{
        let Sock = new SockJS('http://localhost:8080/ws');
        stompClient = over(Sock);
        stompClient.connect({},onConnected, onError);
    }

    const onConnected = () => {
       // stompClient.subscribe('/shoppingCart/public', onCountReceived);
        stompClient.subscribe('/shoppingCartCountProduct/public', getShoppingCartCountProduct);
    sendCountProductInCart();
    }

    const sendCountProductInCart =()=>{
        stompClient.send("/app/getCountProductInCart", {},localStorage.getItem('token'));
    }

    const onCountReceived =(val)=>{
        console.log("navbar= "+val)
        setCountProductInCart(parseInt(val.body))
    }



    const getShoppingCartCountProduct =(val)=>{
        console.log("ShoppingCartCountProduct2= "+val.body)
        setCountProductInCart(parseInt(val.body))
    }

    const onError = (err) => {
        console.log(err);
    }

    useEffect(() => {
       connect();
    }, []);

    return (
        <>
            <div className="site-navbar bg-white py-2">

                <div className="search-wrap">
                    <div className="container">
                        <a href="#" className="search-close js-search-close"><span className="icon-close2"></span></a>
                        <form action="#" method="post">
                            <input type="text" className="form-control" placeholder="Search keyword and hit enter..."/>
                        </form>
                    </div>
                </div>

                <div className="container">
                    <div className="d-flex align-items-center justify-content-between">
                        <div className="logo">
                            <div className="site-logo">
                                <a href="index.html" className="js-logo-clone">ShopMax</a>
                            </div>
                        </div>
                        <div className="main-nav d-none d-lg-block">
                            <nav className="site-navigation text-right text-md-center" role="navigation">
                                <ul className="site-menu js-clone-nav d-none d-lg-block">
                                    <li className="has-children ">
                                        <Link to="/">Home</Link>
                                        <ul className="dropdown">
                                            <li><a href="#">Menu One</a></li>
                                            <li><a href="#">Menu Two</a></li>
                                            <li><a href="#">Menu Three</a></li>
                                            <li className="has-children">
                                                <a href="#">Sub Menu</a>
                                                <ul className="dropdown">
                                                    <li><a href="#">Menu One</a></li>
                                                    <li><a href="#">Menu Two</a></li>
                                                    <li><a href="#">Menu Three</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>

                                    <li className="active"><a href="shop.html">Shop</a></li>
                                    <li><Link to="/dashboard">Catalogue</Link></li>
                                    {/*<li><a href="#">New Arrivals</a></li>*/}
                                    {/*<li><a href="contact.html">Contact</a></li>*/}
                                </ul>
                            </nav>
                        </div>
                        <div className="icons">
                            <a href="#" className="icons-btn d-inline-block js-search-open"><span
                                className="icon-search"></span></a>
                            <a href="#" className="icons-btn d-inline-block"><span className="icon-heart-o"></span></a>
                            <Link to="/cart" className="icons-btn d-inline-block bag">
                                <span className="icon-shopping-bag"></span>
                                <span className="number">{countProductInCart}</span>
                            </Link>
                            <Link to="/login" className="icons-btn d-inline-block"><span
                                className="icon-person"></span></Link>
                            <a href="#" className="site-menu-toggle js-menu-toggle ml-3 d-inline-block d-lg-none"><span
                                className="icon-menu"></span></a>
                        </div>
                    </div>
                </div>
            </div>

        </>

    );
}
export default Navbar;
