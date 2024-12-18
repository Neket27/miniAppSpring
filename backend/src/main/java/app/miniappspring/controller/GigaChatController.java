package app.miniappspring.controller;

import app.miniappspring.chat.GigaChatService;
import app.miniappspring.dto.chat.RequestOnAnswerGigaChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class GigaChatController {
    private final GigaChatService gigaChatService;

    @PostMapping("/models/GigaChat/messages")
    public RequestOnAnswerGigaChatDto getResponse(@RequestBody RequestOnAnswerGigaChatDto requestOnAnswerDto){
        return gigaChatService.getResponseFromGigaChat(requestOnAnswerDto);
    }
}
