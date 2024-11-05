export interface IDiscountCreate {
    name: string;
    amount: number;
    timeLiveInHour:number;
    city:string;
    productIdList:Array<number>;
}