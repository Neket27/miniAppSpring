package app.miniappspring.utils.mapper;

import app.miniappspring.dto.support.CreateSupportMessageDto;
import app.miniappspring.dto.support.SupportMessageDto;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T16:17:56+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class SupportMapperImpl implements SupportMapper {

    @Override
    public Support toEntity(CreateSupportMessageDto createSupportMessageDto) {
        if ( createSupportMessageDto == null ) {
            return null;
        }

        Support support = new Support();

        support.setNameUser( createSupportMessageDto.getNameUser() );
        support.setEmail( createSupportMessageDto.getEmail() );
        support.setMessage( createSupportMessageDto.getMessage() );

        return support;
    }

    @Override
    public SupportMessageDto toDto(Support support) {
        if ( support == null ) {
            return null;
        }

        SupportMessageDto supportMessageDto = new SupportMessageDto();

        supportMessageDto.setId( support.getId() );
        supportMessageDto.setNameUser( support.getNameUser() );
        supportMessageDto.setEmail( support.getEmail() );
        supportMessageDto.setMessage( support.getMessage() );

        return supportMessageDto;
    }
}
