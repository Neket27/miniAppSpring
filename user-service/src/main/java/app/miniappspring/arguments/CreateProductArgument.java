package app.miniappspring.arguments;

import app.miniappspring.dto.image.CreateImageDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductArgument {
    private String category;
    private String name;
    private float cost;
    private float rating;
    private String description;
    private String brand;
    private String note;
    private boolean available;
    private int stock;
    private String detail;
    private String producerCountry;
    private int sellerWarranty;
    private List<CreateImageDto> createImageDtoList;
}
