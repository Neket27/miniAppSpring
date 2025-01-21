package app.miniappspring.dto.product.category;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class CategoryDto implements Serializable {
    private String categoryProduct;
}
