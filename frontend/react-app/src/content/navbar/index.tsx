import React, {createContext, useContext, useEffect, useRef, useState} from "react";
import '../../init';
import '../../fileTemplate/navbar/style.css';
import '../../fileTemplate/navbar/fonts/icomoon/style.css';
import '../../fileTemplate/navbar/aos.css';
import '../../fileTemplate/navbar/bootstrap/bootstrap-grid.css';
import '../../fileTemplate/navbar/bootstrap/bootstrap-reboot.css';
import '../../fileTemplate/navbar/bootstrap.min.css';
import '../../fileTemplate/navbar/jquery-ui.css';
import '../../fileTemplate/navbar/magnific-popup.css';
import '../../fileTemplate/navbar/owl.carousel.min.css';
import '../../fileTemplate/navbar/owl.theme.default.min.css';
import '../../fileTemplate/navbar/fonts/icomoon/demo-files/demo.css';
import {Link} from "react-router-dom";
import {ContextService, locationDetector, State} from "../../main";
import CitySelector from "../сitySelector/CitySelector";
import cities from '../../../../react-app/src/russian-cities.json'
import {string} from "prop-types";

export let ContextCountProductInBag:React.Context<CountProductInBag>=createContext<CountProductInBag>();

export interface CountProductInBag{
    setCountProductInBag:any
    countProductInBag:number
    getCountProductInBag:any
}

const Navbar = () => {
    const contextService:State= useContext(ContextService);
    const [countProductInBag, setCountProductInBag] = useState(0);
    const [city, setCity] = useState<string | null>(null);
    const [availableCities, setAvailableCities] = useState<string[]>([]);
    const [isSelectorVisible, setSelectorVisible] = useState(false); // Видимость селектора

    const countProduct :CountProductInBag={
        setCountProductInBag:setCountProductInBag,
        countProductInBag:countProductInBag,
        getCountProductInBag:getCountProductInBag
    }

    async function getCountProductInBag() {
       const response:number = await contextService.bagService.getCountProductInBag();
       if(response!=null || contextService.authService.isAuth)
        setCountProductInBag(response);
       else
           setCountProductInBag(0);
    }

    const getCity = async () => {
        const city:string = localStorage.getItem('city');
        if(city=="" && city==null)
            setCity(await locationDetector.getCity()); // Обновляем состояние города
        else {
            setCity(city);
            locationDetector.setCity(city);
        }
    };

    const handleCityClick = async () => {
        setSelectorVisible(true);
        setCity("");

    };

    const handleCitySelect = (selectedCity) => {
        localStorage.setItem('city', selectedCity);
        setCity(selectedCity);
        setSelectorVisible(false);
        window.location.reload();
    };

    useEffect(() => {
        ContextCountProductInBag = createContext<CountProductInBag>(countProduct);
            getCountProductInBag();
        const countProductInBag =localStorage.getItem("countProductInBag");
        if(countProductInBag!=null)
            setCountProductInBag(parseInt(countProductInBag));
        else
            setCountProductInBag(0);

        getCity();

        const citiesName :Array<string>=cities.map(city =>city.name);
        setAvailableCities(citiesName);

    }, [city]);



    // Компонент для закрытия при клике снаружи
    const OutsideClickHandler = ({ onOutsideClick, children }) => {
        const ref = useRef();

        useEffect(() => {
            const handleClickOutside = (event) => {
                if (ref.current && !ref.current.contains(event.target)) {
                    onOutsideClick();
                }
            };

            document.addEventListener("mousedown", handleClickOutside);
            return () => {
                document.removeEventListener("mousedown", handleClickOutside);
            };
        }, [onOutsideClick]);

        return <div ref={ref}>{children}</div>;
    };



    return (
        <ContextCountProductInBag.Provider value={{ countProductInBag, setCountProductInBag,getCountProductInBag}}>
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
                                <Link to="/" className="js-logo-clone">White Dream</Link>
                            </div>
                        </div>
                        <p onClick={handleCityClick}>Ваш город: {city}</p>
                        {isSelectorVisible && (
                            <OutsideClickHandler onOutsideClick={() => setSelectorVisible(false)}>
                                <CitySelector
                                    availableCities={availableCities}
                                    onSelectCity={handleCitySelect}
                                />
                            </OutsideClickHandler>
                        )}
                        <div className="main-nav d-none d-lg-block">
                            <nav className="site-navigation text-right text-md-center" role="navigation">
                                <ul className="site-menu js-clone-nav d-none d-lg-block">
                                    <li className="has-children ">
                                        <Link to="/">Главная</Link>
                                        <ul className="dropdown">
                                            <li><a href="#">Доставка</a></li>
                                            <li><a href="#">Обмен, возврат, гарантия</a></li>
                                            <li><Link to="/help">Поддержка</Link></li>
                                            {/*<li className="has-children">*/}
                                            {/*    <a href="#">Sub Menu</a>*/}
                                            {/*    <ul className="dropdown">*/}
                                            {/*        <li><a href="#">Menu One</a></li>*/}
                                            {/*        <li><a href="#">Menu Two</a></li>*/}
                                            {/*        <li><a href="#">Menu Three</a></li>*/}
                                            {/*    </ul>*/}
                                            {/*</li>*/}
                                        </ul>
                                    </li>

                                    <li className="active"><a href="shop.html">Акции</a></li>
                                    <li><Link to="/aboutUs">Кто мы</Link></li>
                                </ul>
                            </nav>
                        </div>
                        <div className="icons">
                            <a href="#" className="icons-btn d-inline-block js-search-open"><span
                                className="icon-search"></span></a>
                            <a href="#" className="icons-btn d-inline-block"><span className="icon-heart-o"></span></a>
                            <Link to="/cart" className="icons-btn d-inline-block bag">
                            <span className="icon-shopping-bag">
                                {/*<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"*/}
                                {/*     className="bi bi-bag" viewBox="0 0 16 16"><path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1m3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1z"/>*/}
                                {/*</svg>*/}
                            </span>
                                <span className="number">{countProductInBag}</span>
                            </Link>
                            {contextService.authService.isAuth ?
                                <Link to="/cabinetUser" className="icons-btn d-inline-block">
                                    <span className="person-username">{contextService.authService.user.username}</span>
                                </Link> :
                                <Link to="/login" className="icons-btn d-inline-block">
                                    <span className="icon-person"></span>
                                </Link>
                            }


                            {/*<svg xmlns="http://www.w3.org/2000/svg" width="23" height="23" fill="currentColor"*/}
                            {/*     className="bi bi-person" viewBox="0 0 16 16"><path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>*/}
                            {/*</svg>*/}

                            <a href="#" className="site-menu-toggle js-menu-toggle ml-3 d-inline-block d-lg-none"><span
                                className="icon-menu"></span></a>
                        </div>
                    </div>
                </div>

                <link rel="stylesheet"
                      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>

                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
                      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                      crossOrigin="anonymous"/>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                        crossOrigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                        crossOrigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
                        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
                        crossOrigin="anonymous"></script>
            </div>
        </ContextCountProductInBag.Provider>
    );
};

export default Navbar;