package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.dto.support.CreateSupportMessageDto;
import app.miniappspring.dto.support.SupportMessageDto;
import app.miniappspring.entity.Support;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupportMapper {
    Support toEntity(CreateSupportMessageDto createSupportMessageDto);
    SupportMessageDto toDto(Support support);
}
