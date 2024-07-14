import {ProductCartResponse} from "../../model/response/product/ProductCartResponse";
import BagController from "../../controller/BagController";
import {Client} from '@stomp/stompjs';
import SockJS from "sockjs-client";
import {IProductInBag} from "../../model/bag/IProductInBag";

class BagService {
    private _stompClient: Client= new Client();

    constructor() {
    }
      async  getProductsFromCart(accessToken: string):Promise<ProductCartResponse> {
        try {
            const response = await BagController.getProductsFromCart(accessToken);
            return {
                productsInCard: response
            };
        } catch (error) {
            console.error("Error fetching products from bag:", error);
            throw error;
        }
    }

      async  getProductFromCart(idProduct:number,accessToken: string):Promise<ProductCartResponse> {
        try {
            return await BagController.getProductFromCart(idProduct, accessToken); // Возвращаем response
        } catch (error) {
            console.error("Error fetching products from bag:", error);
            throw error;
        }
    }

       async addProductInCart(productCart:IProductInBag){
        return await BagController.addProductInCart(productCart);
    }

      async  removeProductFromCart(idProduct: number, accessToken: string):Promise<ProductCartResponse> {
        const response = await BagController.removeProductFromCart(idProduct, accessToken);
        return {
            productsInCard: response
        };
    }

      async  increaseProductInCart(idProduct: number, accessToken: string) {
        return await BagController.increaseProductInCart(idProduct, accessToken);
    }

      async  decreaseProductInCart(idProduct: number,  accessToken: string) {
        return await BagController.decreaseProductInCart(idProduct, accessToken);
    }

    // @ts-ignore
    async getCountProductInBag():Promise<number> {
        const accessToken = localStorage.getItem('accessToken');
        if(accessToken!=null) {
            const count =await BagController.getCountProductInBag(accessToken);
           localStorage.setItem('countProductInBag', String(count));
           return count;
        }
        else {
            console.log("Отсутствует accessToken) при получении количества продуктов в корзине пользователя")
            localStorage.setItem('countProductInBag', String(0));
            return 0;
        }
    }

      async  sendCountProductInCart(idProduct: number,count:number, accessToken: string) {
        return await BagController.sendCountProductInCart(idProduct, count, accessToken);
    }

    public connect(callback:any,bodyTopic:string|null) {
        this._stompClient = new Client({
            webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
            onConnect: (frame) => {
                console.log('Connected: ' + frame);
                this.subscribeToFirstTopic(callback);
                if(bodyTopic!=null)
                    this.sendMessage("/app/getCountProductInCart",bodyTopic)
                else
                    console.log("bodyTopic в connect = "+bodyTopic);
            },
            onStompError: (frame) => {
                console.error('Broker reported error: ' + frame.headers['message']);
                console.error('Additional details: ' + frame.body);
            },
        });

        this._stompClient.activate();
    }

    subscribeToFirstTopic(callback:any) {
        this._stompClient.subscribe('/shoppingCartCountProduct/public', callback);
    }

    private sendMessage(destination:string, message:string) {
        if (this._stompClient && this._stompClient.connected) {
            this._stompClient.publish({
                destination: destination,
                body: message,
            });
        }
    }

    public sendRequestOnGetCountProductInBag(accessToken:string|null):void{
        if(accessToken!=null)
            this.sendMessage("/app/getCountProductInCart",accessToken);
        else
            console.log("Access token в sendRequestOnGetCountProductInCart = "+accessToken)
    }

   }

export default BagService;
