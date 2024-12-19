package app.miniappspring.service.impl;

import app.miniappspring.dto.support.CreateSupportMessageDto;
import app.miniappspring.dto.support.SupportMessageDto;
import app.miniappspring.entity.Image;
import app.miniappspring.entity.Support;
import app.miniappspring.repository.SupportRepo;
import app.miniappspring.service.SupportService;
import app.miniappspring.utils.mapper.ImageMapper;
import app.miniappspring.utils.mapper.SupportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService {
    private final SupportRepo supportRepo;
    private final SupportMapper supportMapper;
    private final ImageMapper imageMapper;

    @Override
    @Transactional
    public SupportMessageDto addMessageUserOboutHelp(CreateSupportMessageDto createSupportMessageDto) {
        Support support = supportMapper.toEntity(createSupportMessageDto);
        List<Image> imageList =imageMapper.toImageListFromCreate(createSupportMessageDto.getImageDtoList());
        support.setImageList(imageList);
        support=supportRepo.save(support);

        SupportMessageDto supportMessageDto =supportMapper.toDto(support);
        supportMessageDto.setImageDtoList(imageMapper.toImageDtoList(support.getImageList()));
        return supportMessageDto;
    }

}
