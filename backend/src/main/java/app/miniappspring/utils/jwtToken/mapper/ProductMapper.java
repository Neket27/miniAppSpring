package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.arguments.UpdateProductArgument;
import app.miniappspring.dto.characteristic.CharacteristicProductDto;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.dto.product.UpdateProductDto;
import app.miniappspring.entity.CharacteristicProduct;
import app.miniappspring.entity.Product;
import app.miniappspring.repository.ImageProductRepo;
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
                .note(product.getNote())
                .description(product.getDescription())
                .available(product.isAvailable())
                .imageDtoList(imageMapper.toImageDtoList(product.getImageList()))
                .build();
    }

    public abstract Product toProduct(CreateProductArgument createProductArgument);
    public abstract Product toProduct(UpdateProductDto updateProductDto);

    public Product updateProduct(Product product,UpdateProductDto updateProductDto){
        CharacteristicProduct characteristicProduct =product.getCharacteristicProduct();
        characteristicProduct.setSellerWarranty(updateProductDto.getCharacteristic().getSellerWarranty());
        characteristicProduct.setProducerCountry(updateProductDto.getCharacteristic().getProducerCountry());

        product.setId(updateProductDto.getId());
        product.setName(updateProductDto.getName());
        product.setCost(updateProductDto.getCost());
        product.setDetail(updateProductDto.getDetail());
        product.setCharacteristicProduct(characteristicProduct);
        product.setBrand(updateProductDto.getBrand());
        product.setStock(updateProductDto.getStock());
        product.setNote(updateProductDto.getName());
        product.setDescription(updateProductDto.getDescription());
        product.setAvailable(updateProductDto.isAvailable());
        product.getImageList().clear();
        product.getImageList().addAll(imageMapper.toImageListUpdate(updateProductDto.getUpdateImageDtoList()));
        return product;
    }

    public UpdateProductDto toUpdateProductDto(UpdateProductArgument updateProductArgument, CharacteristicProduct characteristicProduct) {
CharacteristicProductDto characteristicProductDto = characteristicMapper.toCharacteristicProductDto(characteristicProduct);

        return UpdateProductDto.builder()
                .id(updateProductArgument.getId())
                .category(updateProductArgument.getCategory())
//                .subcategory(updateProductArgument.getSubcategory())
                .name(updateProductArgument.getName())
                .cost(updateProductArgument.getCost())
                .detail(updateProductArgument.getDetail())
                .characteristic(characteristicProductDto)
                .brand(updateProductArgument.getBrand())
                .stock(updateProductArgument.getStock())
                .article(updateProductArgument.getArticle())
                .description(updateProductArgument.getDescription())
                .available(updateProductArgument.isAvailable())
                .updateImageDtoList(imageMapper.toUpdateImageDtoList(updateProductArgument.getUpdateImageDtoList()))
                .build();
    }
}
