package app.miniappspring.utils.mapper;


import app.miniappspring.entity.Image;
import app.miniappspring.web.dto.image.CreateImageDto;
import app.miniappspring.web.dto.image.ImageDto;
import app.miniappspring.web.dto.image.UpdateImageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demo.numbers.GRPCImage;

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


    @Mapping(target = "bytes", expression = "java(grpcImage.getBytes().toByteArray())")
    Image fromGRPCImage(GRPCImage grpcImage);

    @Mapping(target = "bytes", expression = "java(com.google.protobuf.ByteString.copyFrom(image.getBytes()))")
    GRPCImage toGRPCImage(Image image);

    List<ImageDto> toImageDtoList(List<Image> images);

    List<UpdateImageDto> toUpdateImageDtoList(List<UpdateImageDto> updateImageDtoList);

    List<Image> toImageListFromCreate(List<CreateImageDto> createImageDtoList);

    List<Image> toImageListFromUpdate(List<UpdateImageDto> updateImageDtoList);

    List<Image> toImageListUpdate(List<UpdateImageDto> updateImagerDtoList);

    default byte[] toBytes(String string) {
        return string != null ? string.getBytes() : null;
    }
}

