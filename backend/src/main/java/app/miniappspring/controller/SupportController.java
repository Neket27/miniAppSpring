package app.miniappspring.controller;

import app.miniappspring.dto.support.CreateSupportMessageDto;
import app.miniappspring.dto.support.SupportMessageDto;
import app.miniappspring.service.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/support")
@RequiredArgsConstructor
public class SupportController {

    private final SupportService supportService;

    @PostMapping("/add/client/help-message")
    public SupportMessageDto addMessageUserOboutHelp(@RequestBody CreateSupportMessageDto createSupportMessageDto) {
        return supportService.addMessageUserOboutHelp(createSupportMessageDto);
    }
}
