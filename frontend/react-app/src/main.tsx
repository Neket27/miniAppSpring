import React, {createContext} from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import {BrowserRouter} from 'react-router-dom';
import AuthService from "./service/auth/AuthService";
import ProductService from "./service/product/ProductService";
import BagService from "./service/bag/BagService";
import CouponService from "./service/couponService/CouponService";
import {DiscountService} from "./service/discount/DiscountService";
import {RatingService} from "./service/rating/RatingService";
import {SupportService} from "./service/support/SupportService";
import {CategoryService} from "./service/category/CategoryService";
import {LocationDetector} from "./utils/LocationDetector";
import {PayService} from "./service/pay/PayService";
import {NeuroChatService} from "./service/neuroChatService/NeuroChatService";

export interface State {
    authService:AuthService;
    productService:ProductService;
    bagService:BagService;
    couponService:CouponService;
    discountService:DiscountService;
    ratingService:RatingService;
    supportService:SupportService;
    categoryService:CategoryService;
    payService:PayService;
    neuroChatService:NeuroChatService;
}
export const locationDetector = new LocationDetector()

const authService:AuthService = new AuthService();
const productService:ProductService = new ProductService();
const bagService:BagService = new BagService();
const couponService:CouponService = new CouponService();
const discountService:DiscountService = new DiscountService();
const ratingService:RatingService = new RatingService();
const supportService:SupportService = new SupportService();
const categoryService:CategoryService = new CategoryService();
const payService:PayService = new PayService();
const neuroChatService = new NeuroChatService();

export const ContextService:React.Context<State> = createContext<State>({
    authService,
    productService,
    bagService,
    couponService,
    discountService,
    ratingService,
    supportService,
    categoryService,
    payService,
    neuroChatService
});


ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
      <BrowserRouter>
      <ContextService.Provider value={{authService,productService,bagService,couponService,discountService,ratingService,supportService,categoryService,payService,neuroChatService}}>
        <App/>
      </ContextService.Provider>
      </BrowserRouter>
  </React.StrictMode>,
)
