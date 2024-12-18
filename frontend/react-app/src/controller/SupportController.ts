import api from "../http";
import {ICreateSupportMessage} from "../model/support/ICreateSupportMessage";

export class SupportController {

    public static async addSupportMessage(createSupportMessage:ICreateSupportMessage){
       return  api.post<ICreateSupportMessage>('api/v1/support/add/client/help-message',createSupportMessage).then(r=>r.data);
    }
}