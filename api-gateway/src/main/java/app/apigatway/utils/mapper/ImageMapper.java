package app.apigatway.utils.mapper;

import app.apigatway.dto.image.CreateImageDto;
import app.apigatway.dto.image.ImageDto;
import app.apigatway.dto.image.UpdateImageDto;
import app.apigatway.entity.Image;
import org.mapstruct.Mapper;

import java.util.Base64;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image toImage(ImageDto imageDto);


    default Image toImage(CreateImageDto createImageDto) {
        return Image.builder()
                .name(createImageDto.getName())
                .contentType(createImageDto.getContentType())
                .bytes(Base64.getDecoder().decode(createImageDto.getBase64()))
                .build();
    }


    default Image toImage(UpdateImageDto updateImagerDto) {
        return Image.builder()
                .name(updateImagerDto.getName())
                .contentType(updateImagerDto.getContentType())
                .bytes(Base64.getDecoder().decode(updateImagerDto.getBase64()))
                .build();
    }

    default ImageDto toImageDto(Image image) {
        return ImageDto.builder()
                .name(image.getName())
                .contentType(image.getContentType())
                .base64(Base64.getEncoder().encodeToString(image.getBytes()))
                .build();
    }

    List<ImageDto> toImageDtoList(List<Image> images);

    List<UpdateImageDto> toUpdateImageDtoList(List<UpdateImageDto> updateImageDtoList);

    List<Image> toImageListFromCreate(List<CreateImageDto> createImageDtoList);

    List<Image> toImageListFromUpdate(List<UpdateImageDto> updateImageDtoList);

    List<Image> toImageListUpdate(List<UpdateImageDto> updateImagerDtoList);

    default byte[] toBytes(String string) {
        return string != null ? string.getBytes() : null;
    }
}

