import {NeuroChatDto} from "../../model/neuroChat/NeuroChatDto";
import {ChatNeuroChatController} from "../../controller/ChatNeuroChatController";
import {NeuroChatResponse} from "../../model/neuroChat/NeuroChatResponse";
import {ICardProduct} from "../../model/product/ICardProduct";
import {ResponseOpenAi} from "../../model/neuroChat/ResponseOpenAi";

export class NeuroChatService {

    async getAnswerFromNeuroChat(neuroChatMessage: NeuroChatDto):Promise<NeuroChatResponse> {
        return ChatNeuroChatController.getAnswerFromNeuroChat(neuroChatMessage);
    }

    async sendQuestionInNero(question:String):Promise<ResponseOpenAi>{
        return ChatNeuroChatController.sendQuestionInNero(question);
    }
}