package app.miniappspring.service;

import app.miniappspring.dto.chat.RequestOnAnswerGigaChatDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface GigaChatService {
    RequestOnAnswerGigaChatDto getResponseFromGigaChat(RequestOnAnswerGigaChatDto requestOnAnswerDto);

}
