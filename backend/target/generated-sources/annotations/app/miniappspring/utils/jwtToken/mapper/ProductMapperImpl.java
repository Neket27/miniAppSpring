package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.dto.product.UpdateProductDto;
import app.miniappspring.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-06T00:17:19+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl extends ProductMapper {

    @Override
    public Product toProduct(CreateProductArgument createProductArgument) {
        if ( createProductArgument == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( createProductArgument.getName() );
        product.cost( createProductArgument.getCost() );
        product.rating( createProductArgument.getRating() );
        product.description( createProductArgument.getDescription() );
        product.brand( createProductArgument.getBrand() );
        product.article( createProductArgument.getArticle() );
        product.available( createProductArgument.isAvailable() );
        product.stock( createProductArgument.getStock() );
        product.detail( createProductArgument.getDetail() );
        product.subcategory( createProductArgument.getSubcategory() );

        return product.build();
    }

    @Override
    public Product toProduct(UpdateProductDto updateProductDto) {
        if ( updateProductDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( updateProductDto.getId() );
        product.name( updateProductDto.getName() );
        product.cost( updateProductDto.getCost() );
        product.rating( updateProductDto.getRating() );
        product.description( updateProductDto.getDescription() );
        product.brand( updateProductDto.getBrand() );
        product.article( updateProductDto.getArticle() );
        product.available( updateProductDto.isAvailable() );
        product.stock( updateProductDto.getStock() );
        product.detail( updateProductDto.getDetail() );
        product.subcategory( updateProductDto.getSubcategory() );

        return product.build();
    }
}
