package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

//@Mapper(componentModel = "spring", uses = CategoryProductMapper.class)
@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    // @Autowired
    //  private CategoryProductMapper categoryProductEnumMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private CharacteristicMapper characteristicMapper;

    public ProductCardDto toProductCardDto(Product product){
        return ProductCardDto.builder()
                .id(product.getId())
                .name(product.getName())
                .cost(product.getCost())
                .rating(product.getRating())
//                .category(product.getCategoryProduct())
                .imageDtoList(imageMapper.toImageDtoList(product.getImageList()))
                .build();
    }
    //public abstract Product toProduct(ProductCardDto productCardDto);
    //public abstract Product toProduct(CreateProductDto createProductDto);
    public ProductDetailDto toProductDetailDto(Product product){
        return ProductDetailDto.builder()
                .id(product.getId())
                .name(product.getName())
                .cost(product.getCost())
                .detail(product.getDetail())
                //   .categoryProductDto(categoryMapper.toCategoryProductDto(product.getCategoryProduct()))
                .characteristicProductDto(characteristicMapper.toCharacteristicProductDto(product.getCharacteristicProduct()))
                .brand(product.getBrand())
                .stock(product.getStock())
                .article(product.getArticle())
                .description(product.getDescription())
                .available(product.isAvailable())
                //  .subcategory(product.getSubcategory())
                .article(product.getArticle())
                .imageDtoList(imageMapper.toImageDtoList(product.getImageList()))
                .build();
    }
    public abstract Product toProduct(CreateProductArgument createProductArgument);
    // public abstract CreateProductDto toCreateProductDto(CreateProductArgument createProductArgument);


}
