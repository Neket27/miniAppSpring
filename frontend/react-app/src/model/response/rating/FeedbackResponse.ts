export interface FeedbackResponse {
    id:number,
    idProduct:number;
    nameUser:string;
    email:string;
    photoUser:number;
    message:string;
    evaluation:number;
    imageList:Array<string>;
    date:string;
}