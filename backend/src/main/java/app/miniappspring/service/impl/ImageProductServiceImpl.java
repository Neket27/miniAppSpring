package app.miniappspring.service.impl;

import app.miniappspring.repository.ImageRepo;
import app.miniappspring.service.ImageProductService;
import app.miniappspring.utils.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ImageProductServiceImpl implements ImageProductService {
    private final ImageRepo imageProductRepo;
    private final ImageMapper imageMapper;

}

