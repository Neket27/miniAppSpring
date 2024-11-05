package app.miniappspring.service;

import app.miniappspring.dto.support.CreateSupportMessageDto;
import app.miniappspring.dto.support.SupportMessageDto;

public interface SupportService {

    SupportMessageDto addMessageUserOboutHelp(CreateSupportMessageDto createSupportMessageDto);
}
