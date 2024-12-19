package app.miniappspring.service;

import app.miniappspring.dto.image.CreateImageDto;
import app.miniappspring.dto.image.UpdateImageDto;
import app.miniappspring.entity.Image;

import java.util.Collection;
import java.util.List;

public interface r {
    String toy(String a);
    String toy(Integer b);

    List<Image> toImageList(Collection<CreateImageDto> createImageDtoList);
    List<Image> toImageList(List<UpdateImageDto> updateImageDtoList);
}
