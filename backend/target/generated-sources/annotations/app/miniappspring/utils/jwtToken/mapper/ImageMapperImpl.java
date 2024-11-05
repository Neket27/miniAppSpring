package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.dto.image.CreateImageDto;
import app.miniappspring.dto.image.ImageDto;
import app.miniappspring.dto.image.UpdateImageDto;
import app.miniappspring.entity.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-06T00:17:19+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ImageMapperImpl implements ImageMapper {

    @Override
    public Image toImage(MultipartFile multipartFile) throws IOException {
        if ( multipartFile == null ) {
            return null;
        }

        Image.ImageBuilder image = Image.builder();

        image.name( multipartFile.getName() );
        image.contentType( multipartFile.getContentType() );
        byte[] bytes = multipartFile.getBytes();
        if ( bytes != null ) {
            image.bytes( Arrays.copyOf( bytes, bytes.length ) );
        }

        return image.build();
    }

    @Override
    public List<ImageDto> toImageDtoList(List<Image> images) {
        if ( images == null ) {
            return null;
        }

        List<ImageDto> list = new ArrayList<ImageDto>( images.size() );
        for ( Image image : images ) {
            list.add( toImageDto( image ) );
        }

        return list;
    }

    @Override
    public List<UpdateImageDto> toUpdateImageDtoList(List<UpdateImageDto> updateImageDtoList) {
        if ( updateImageDtoList == null ) {
            return null;
        }

        List<UpdateImageDto> list = new ArrayList<UpdateImageDto>( updateImageDtoList.size() );
        for ( UpdateImageDto updateImageDto : updateImageDtoList ) {
            list.add( updateImageDto );
        }

        return list;
    }

    @Override
    public List<Image> toImageList(List<CreateImageDto> createImageDtoList) {
        if ( createImageDtoList == null ) {
            return null;
        }

        List<Image> list = new ArrayList<Image>( createImageDtoList.size() );
        for ( CreateImageDto createImageDto : createImageDtoList ) {
            list.add( toImage( createImageDto ) );
        }

        return list;
    }

    @Override
    public List<Image> toImageList2(List<UpdateImageDto> updateImageDtoList) {
        if ( updateImageDtoList == null ) {
            return null;
        }

        List<Image> list = new ArrayList<Image>( updateImageDtoList.size() );
        for ( UpdateImageDto updateImageDto : updateImageDtoList ) {
            list.add( toImage( updateImageDto ) );
        }

        return list;
    }

    @Override
    public List<Image> toImageListUpdate(List<UpdateImageDto> updateImagerDtoList) {
        if ( updateImagerDtoList == null ) {
            return null;
        }

        List<Image> list = new ArrayList<Image>( updateImagerDtoList.size() );
        for ( UpdateImageDto updateImageDto : updateImagerDtoList ) {
            list.add( toImage( updateImageDto ) );
        }

        return list;
    }
}
