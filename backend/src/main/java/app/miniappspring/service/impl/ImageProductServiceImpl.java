package app.miniappspring.service.impl;

import app.miniappspring.dto.image.CreateImageDto;
import app.miniappspring.dto.image.ImageDto;
import app.miniappspring.dto.image.UpdateImageDto;
import app.miniappspring.entity.Image;
import app.miniappspring.entity.Product;
import app.miniappspring.repository.ImageProductRepo;
import app.miniappspring.service.ImageProductService;
import app.miniappspring.utils.jwtToken.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageProductServiceImpl implements ImageProductService {
    private final ImageProductRepo imageProductRepo;
    private final ImageMapper imageMapper;

    @Override
    @Transactional
    public List<ImageDto> saveAllAndGetListImageDto(List<CreateImageDto> createImageDtoList){
        return imageMapper.toImageDtoList(imageProductRepo.saveAll(imageMapper.toImageList(createImageDtoList)));
    }

    @Override
    @Transactional
    public List<Image> saveAllAndGetListImage(List<CreateImageDto>createImageDtoList, Product product){
        List<Image> imageList= imageMapper.toImageList(createImageDtoList).stream().map(e->{
            e.setProduct(product);
            return e;
        }).toList();
        return imageProductRepo.saveAll(imageList);
//       return imageMapper.toImageList(createImageDtoList).stream().map(imageProductRepo::save).toList();
    }

    @Override
    @Transactional
    public List<Image> saveAllAndGetListImage2(List<UpdateImageDto> updateImageDtoList, Product product) {
//        imageProductRepo.deleteAllByProduct_Id(product.getId());
        product.getImageList().clear();
        List<Image> imageList= imageMapper.toImageList2(updateImageDtoList).stream().map(e->{
            e.setProduct(product);
            return e;
        }).toList();
        return imageProductRepo.saveAll(imageList);
    }
}

