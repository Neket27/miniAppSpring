import api from "../http";
import {IFeedback} from "../model/rating/IFeedback";
import {FeedbackResponse} from "../model/response/rating/FeedbackResponse";

export class RatingController {

   public static async getFeedbackList(idProduct:number):Promise<Array<FeedbackResponse>> {
      return api.get<Array<FeedbackResponse>>(`/api/v1/feedback/list?idProduct=`+idProduct).then(r=>r.data);
    }

    static async addFeedback(feedback:IFeedback){
        return api.post<IFeedback>('/api/v1/feedback/add',feedback)
            .then(response=>response.data);
    }

    static async getProductRatings(idProduct:number){
        return api.get<Array<FeedbackResponse>>('/api/v1/feedback')
            .then(response=>response.data);
    }

}