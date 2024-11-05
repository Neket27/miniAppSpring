import {ICreateSupportMessage} from "../../model/support/ICreateSupportMessage";
import {SupportController} from "../../controller/SupportController";

export class SupportService {

    public async addSupportMessage(createSupportMessage:ICreateSupportMessage){
        await SupportController.addSupportMessage(createSupportMessage);
    }
}