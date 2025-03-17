import api from "../http";
import {NeuroChatDto} from "../model/neuroChat/NeuroChatDto";
import {NeuroChatResponse} from "../model/neuroChat/NeuroChatResponse";
import {ResponseOpenAi} from "../model/neuroChat/ResponseOpenAi";

export class ChatNeuroChatController {

    public static async getAnswerFromNeuroChat(neuroChatMessage: NeuroChatDto) {
        // return api.post<NeuroChatResponse>("api/v1/models/GigaChat/messages", neuroChatMessage).then(r => r.data);
        return api.post<NeuroChatResponse>("api/v1/mistral/chat2?prompt=" + neuroChatMessage.message).then(r => r.data);
    }

    static sendQuestionInNero(question: String) {
        return api.post<ResponseOpenAi>("/api/v1/nero/filtred-products?message=" + question).then(r => r.data);
    }
}