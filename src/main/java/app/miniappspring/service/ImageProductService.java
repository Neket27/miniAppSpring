package app.miniappspring.service;

import app.miniappspring.dto.image.CreateImageDto;
import app.miniappspring.dto.image.ImageDto;
import app.miniappspring.entity.Image;
import app.miniappspring.entity.Product;

import java.util.List;

public interface ImageProductService {

    List<ImageDto> saveAllAndGetListImageDto(List<CreateImageDto> createImageDtoList);
    List<Image> saveAllAndGetListImage(List<CreateImageDto> createImageDtoList, Product product);

}
