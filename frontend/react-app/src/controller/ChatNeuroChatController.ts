import api from "../http";
import {NeuroChatDto} from "../model/neuroChat/NeuroChatDto";
import {NeuroChatResponse} from "../model/neuroChat/NeuroChatResponse";

export class ChatNeuroChatController {

    public static async getAnswerFromNeuroChat(neuroChatMessage: NeuroChatDto) {
        // return api.post<NeuroChatResponse>("api/v1/models/GigaChat/messages", neuroChatMessage).then(r => r.data);
        return api.post<NeuroChatResponse>("api/v1/mistral/chat2?prompt="+neuroChatMessage.message).then(r => r.data);
    }
}