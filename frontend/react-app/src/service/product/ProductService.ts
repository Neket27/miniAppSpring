import {ICardProduct} from "../../model/product/ICardProduct";
import ProductController from "../../controller/ProductController";
import {CardProductResponse} from "../../model/response/product/CardProductResponse";
import {IDetailProduct} from "../../model/product/IDetailProduct";
import BagController from "../../controller/BagController";
import {ProductCartResponse} from "../../model/response/product/ProductCartResponse";
import {IFeedback} from "../../model/rating/IFeedback";
import {Client} from "@stomp/stompjs";
import SockJS from "sockjs-client";

class ProductService {
    private _stompClient: Client = new Client();

    constructor() {
    }

     // @ts-ignore
    async getListProductOnHomePage(): Promise<ICardProduct[]> {
        try{
            const response:Promise<ICardProduct[]> = ProductController.getCardsProduct();
            if(response!=undefined)
                return response;

            return new Array<ICardProduct>();
        }catch (e){
            console.log("Ошибка получения данных в getListProductOnHomePage() = "+e);
        }
    }


    // @ts-ignore
     async getProductByCategory(category: string):Promise<CardProductResponse> {
        try {
            const response:CardProductResponse = await ProductController.getProductsByCategory({ categoryProduct: category, subcategory: 'unsupported', stringValueCategory: 'mmm' });
            return response
        } catch (error) {
            console.error("Error fetching related products:", error);
        }
    }

    // @ts-ignore
     async getProductDetail(idProduct:number): Promise<IDetailProduct> {
        try {
            if(idProduct!=undefined)
                return await ProductController.getProductDetail(idProduct);
            else
                console.error("TypeId for 'getProductDetail'= ", idProduct);

        } catch (error) {
            console.error("Error fetching product detail:", error);
        }
    }

    // @ts-ignore
     async getProductFromCart(idProduct: number, accessToken: string):Promise<ProductCartResponse> {
        try {
            return await BagController.getProductFromCart(idProduct, accessToken);

        } catch (error) {
            console.error("Error fetching product from bag:", error);
        }
    }

     async sendCountProductInCartUser (idProduct: number, count: number, accessToken: string):Promise<void >  {
        if (!isNaN(count)) {  // Проверка на то, что count является числом
            const response = await BagController.sendCountProductInCart(idProduct, count, accessToken);
        } else {
            console.error("Ошибка: Невалидное значение countProductsInBag");
        }
    };
     async addRating(feedback:IFeedback):Promise<void>{
        await ProductController.addRating(feedback);
    };

   public connect(callback:any,bodyTopic_1:string|null,bodyTopic_2:string) {
        this._stompClient = new Client({
            webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
            onConnect: (frame) => {
                console.log('Connected: ' + frame);
                // this.subscribeToTopics(callback);
                this.subscribeToFirstTopic(null);
                this.subscribeToSecondTopic(callback)
                this.sendMessage("/app/getNumberOfPiecesOfGoods",bodyTopic_2);
                if(bodyTopic_1!=null)
                    this.sendMessage("/app/getCountProductInCart",bodyTopic_1);
                else
                    console.log("Access token в connect = "+bodyTopic_1);
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

    subscribeToSecondTopic(callback:any) {
        this._stompClient.subscribe('/shoppingCart/public', callback);
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

    public sendNumberOfPiecesOfGoods(idProduct:number|undefined,count:number,accessToken:string|null){
       if (accessToken!=null)
           if(idProduct!=undefined)
                this.sendMessage("/app/sendNumberOfPiecesOfGoods",JSON.stringify({idProduct: idProduct, count: count, accessToken: accessToken}));
            else
               console.log("Id продукта в sendNumberOfPiecesOfGoods = "+idProduct)
       else
           console.log("Access token в sendNumberOfPiecesOfGoods = "+accessToken)
    }

    public sendRequestOnGetNumberOfPiecesOfGoods(idProduct:number|undefined,accessToken:string|null){
        if (accessToken!=null)
            if(idProduct!=undefined)
                this.sendMessage("/app/getNumberOfPiecesOfGoods",JSON.stringify({idProduct: idProduct, accessToken: accessToken}));
            else
                console.log("Id продукта в sendRequestOnGetNumberOfPiecesOfGoods = "+idProduct);
        else
            console.log("Access token в sendRequestOnGetNumberOfPiecesOfGoods = "+accessToken);
    }
}

export default ProductService;