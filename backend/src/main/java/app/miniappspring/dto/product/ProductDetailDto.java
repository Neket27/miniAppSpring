package app.miniappspring.dto.product;

import app.miniappspring.dto.characteristic.CharacteristicProductDto;
import app.miniappspring.dto.image.ImageDto;
import app.miniappspring.entity.CategoryProduct;
import app.miniappspring.entity.CharacteristicProduct;
import app.miniappspring.entity.Feedback;
import app.miniappspring.entity.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class ProductDetailDto {
   private final Long id;
   private CategoryProduct categoryProduct;
   private String subcategory;
   private String name;
   private Float cost;
   private String description;
   private String brand;
   private String article;
   private boolean available;
   private int stock;
   private CharacteristicProductDto characteristicProductDto;
   private String detail;
   private Feedback feedback;
   List<ImageDto> imageDtoList;
}
