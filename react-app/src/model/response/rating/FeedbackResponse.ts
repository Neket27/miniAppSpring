export interface FeedbackResponse {
    id:number,
    idProduct:number;
    nameUser:string;
    photoUser:number;
    message:string;
    evaluation:number;
    imageList:Array<string>;
    date:Date;
}