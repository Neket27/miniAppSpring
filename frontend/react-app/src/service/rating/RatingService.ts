import {RatingController} from "../../controller/RatingController";
import {IFeedback} from "../../model/rating/IFeedback";
import ProductController from "../../controller/ProductController";

export class RatingService {

    async getFeedbackList(idProduct:number){
       return  await RatingController.getFeedbackList(idProduct);
    }

    async addFeedback(feedback:IFeedback):Promise<void>{
        await RatingController.addFeedback(feedback);
    };

}