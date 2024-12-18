package app.miniappspring.service;

import app.miniappspring.dto.chat.RequestOnAnswerGigaChatDto;

public interface GigaChatService {
    RequestOnAnswerGigaChatDto getResponseFromGigaChat(RequestOnAnswerGigaChatDto requestOnAnswerDto);

}
