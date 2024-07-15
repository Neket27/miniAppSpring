package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-15T01:30:14+0300",
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
}
