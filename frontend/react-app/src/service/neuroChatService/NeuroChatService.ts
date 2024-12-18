import {NeuroChatDto} from "../../model/neuroChat/NeuroChatDto";
import {ChatNeuroChatController} from "../../controller/ChatNeuroChatController";
import {NeuroChatResponse} from "../../model/neuroChat/NeuroChatResponse";

export class NeuroChatService {

    async getAnswerFromNeuroChat(neuroChatMessage: NeuroChatDto):Promise<NeuroChatResponse> {
        return ChatNeuroChatController.getAnswerFromNeuroChat(neuroChatMessage);
    }
}