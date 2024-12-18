package app.miniappspring.dto.product;

import app.miniappspring.dto.characteristic.CharacteristicProductDto;
import app.miniappspring.dto.image.ImageDto;
import app.miniappspring.entity.Feedback;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class ProductDetailDto {
   private final Long id;
   private String category;
//   private String subcategory;
   private String name;
   private Float cost;
   private String description;
   private String brand;
   private String note;
   private boolean available;
   private int stock;
   private CharacteristicProductDto characteristicProductDto;
   private String detail;
   private Feedback feedback;
   List<ImageDto> imageDtoList;
}
