package app.miniappspring.utils.mapper;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.dto.product.UpdateProductDto;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T16:17:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl extends ProductMapper {

    @Override
    public Product toProduct(CreateProductArgument createProductArgument) {
        if ( createProductArgument == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( createProductArgumentToCategory( createProductArgument ) );
        product.setName( createProductArgument.getName() );
        product.setCost( createProductArgument.getCost() );
        product.setRating( createProductArgument.getRating() );
        product.setDescription( createProductArgument.getDescription() );
        product.setBrand( createProductArgument.getBrand() );
        product.setNote( createProductArgument.getNote() );
        product.setAvailable( createProductArgument.isAvailable() );
        product.setStock( createProductArgument.getStock() );
        product.setDetail( createProductArgument.getDetail() );

        return product;
    }

    @Override
    public Product toProduct(UpdateProductDto updateProductDto) {
        if ( updateProductDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( updateProductDtoToCategory( updateProductDto ) );
        product.setId( updateProductDto.getId() );
        product.setName( updateProductDto.getName() );
        product.setCost( updateProductDto.getCost() );
        product.setRating( updateProductDto.getRating() );
        product.setDescription( updateProductDto.getDescription() );
        product.setBrand( updateProductDto.getBrand() );
        product.setAvailable( updateProductDto.isAvailable() );
        product.setStock( updateProductDto.getStock() );
        product.setDetail( updateProductDto.getDetail() );

        return product;
    }

    protected Category createProductArgumentToCategory(CreateProductArgument createProductArgument) {
        if ( createProductArgument == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( createProductArgument.getCategory() );

        return category;
    }

    protected Category updateProductDtoToCategory(UpdateProductDto updateProductDto) {
        if ( updateProductDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( updateProductDto.getCategory() );

        return category;
    }
}
