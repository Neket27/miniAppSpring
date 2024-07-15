package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.dto.product.category.CategoryProductDto;
import app.miniappspring.entity.Category;
import app.miniappspring.entity.CategoryProduct;
import app.miniappspring.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-15T10:42:25+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryProduct toCategoryProduct(CreateProductArgument createProductArgument) {
        if ( createProductArgument == null ) {
            return null;
        }

        CategoryProduct.CategoryProductBuilder categoryProduct = CategoryProduct.builder();

        categoryProduct.stringValueCategory( createProductArgumentCategoryRussianValue( createProductArgument ) );
        categoryProduct.category( createProductArgument.getCategory() );
        categoryProduct.subcategory( createProductArgument.getSubcategory() );

        return categoryProduct.build();
    }

    @Override
    public CategoryDto toCategoryDto(CategoryProduct categoryProduct) {
        if ( categoryProduct == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        if ( categoryProduct.getCategory() != null ) {
            categoryDto.setCategoryProduct( categoryProduct.getCategory().name() );
        }
        categoryDto.setSubcategory( categoryProduct.getSubcategory() );

        return categoryDto;
    }

    @Override
    public CategoryProductDto toCategoryProductDto(CategoryProduct categoryProduct) {
        if ( categoryProduct == null ) {
            return null;
        }

        CategoryProductDto categoryProductDto = new CategoryProductDto();

        categoryProductDto.setCategory( categoryProduct.getCategory() );
        categoryProductDto.setSubcategory( categoryProduct.getSubcategory() );
        categoryProductDto.setStringValueCategory( categoryProduct.getStringValueCategory() );

        return categoryProductDto;
    }

    @Override
    public CategoryProductDto toCategoryProduct(Product product) {
        if ( product == null ) {
            return null;
        }

        CategoryProductDto categoryProductDto = new CategoryProductDto();

        categoryProductDto.setIdProduct( product.getId() );
        categoryProductDto.setNameProduct( product.getName() );
        categoryProductDto.setCategory( productCategoryProductCategory( product ) );
        categoryProductDto.setSubcategory( productCategoryProductSubcategory( product ) );
        categoryProductDto.setStringValueCategory( productCategoryProductStringValueCategory( product ) );

        return categoryProductDto;
    }

    private String createProductArgumentCategoryRussianValue(CreateProductArgument createProductArgument) {
        if ( createProductArgument == null ) {
            return null;
        }
        Category category = createProductArgument.getCategory();
        if ( category == null ) {
            return null;
        }
        String russianValue = category.getRussianValue();
        if ( russianValue == null ) {
            return null;
        }
        return russianValue;
    }

    private Category productCategoryProductCategory(Product product) {
        if ( product == null ) {
            return null;
        }
        CategoryProduct categoryProduct = product.getCategoryProduct();
        if ( categoryProduct == null ) {
            return null;
        }
        Category category = categoryProduct.getCategory();
        if ( category == null ) {
            return null;
        }
        return category;
    }

    private String productCategoryProductSubcategory(Product product) {
        if ( product == null ) {
            return null;
        }
        CategoryProduct categoryProduct = product.getCategoryProduct();
        if ( categoryProduct == null ) {
            return null;
        }
        String subcategory = categoryProduct.getSubcategory();
        if ( subcategory == null ) {
            return null;
        }
        return subcategory;
    }

    private String productCategoryProductStringValueCategory(Product product) {
        if ( product == null ) {
            return null;
        }
        CategoryProduct categoryProduct = product.getCategoryProduct();
        if ( categoryProduct == null ) {
            return null;
        }
        String stringValueCategory = categoryProduct.getStringValueCategory();
        if ( stringValueCategory == null ) {
            return null;
        }
        return stringValueCategory;
    }
}
