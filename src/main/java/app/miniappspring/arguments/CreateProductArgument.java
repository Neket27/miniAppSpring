package app.miniappspring.arguments;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class CreateProductArgument {
    private String name;
    private float cost;
    private float rating;
    private String description;
    private String brand;
    private String article;
    private boolean available;
    private int stock;
    private String detail;
    @Schema(type = "string", format = "binary", description = "Binary data")
    private byte[] image;
    private String producerCountry;
    private int SellerWarranty;
    @ArraySchema(schema = @Schema(type = "string", format = "binary"))
    private List<byte[]> images;
}
