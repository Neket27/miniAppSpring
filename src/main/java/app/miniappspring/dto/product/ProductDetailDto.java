package app.miniappspring.dto.product;

import app.miniappspring.entity.CharacteristicProduct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class ProductDetailDto {
   private final Long id;
   private String name;
   private Float cost;
   private String description;
   private String brand;
   private String article;
   private boolean available;
   private int stock;
   private CharacteristicProduct characteristic;
   private String detail;
}
