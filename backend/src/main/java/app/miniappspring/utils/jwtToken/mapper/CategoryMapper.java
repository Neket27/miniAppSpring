//package app.miniappspring.utils.jwtToken.mapper;

import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
//public interface CategoryMapper{

//    @Mapping(source = "category.russianValue", target="stringValueCategory")
//    CategoryProduct toCategoryProduct(CreateProductArgument createProductArgument);
//    @Mapping(source = "category",target = "categoryProduct")
//    @Mapping(source = "subcategory",target = "subcategory")
//    CategoryDto toCategoryDto(CategoryProduct categoryProduct);
//    CategoryProductDto toCategoryProductDto(CategoryProduct categoryProduct);
//
//    @Mapping(source = "id", target = "idProduct")
//    @Mapping(source = "name",target = "nameProduct")
//    @Mapping(source = "categoryProduct.category", target = "category")
//    @Mapping(source = "categoryProduct.subcategory", target = "subcategory")
//    @Mapping(source = "categoryProduct.stringValueCategory", target = "stringValueCategory")
//    CategoryProductDto toCategoryProduct(Product product);
//
//    default List<CategoryProductDto> toListCategoryProductDto(Set<CategoryProduct> categoryProductSet){
//        return categoryProductSet.stream()
//                .map(this::toCategoryProductDto)
//                .collect(Collectors.toList());
//    }
//
//    default List<CategoryProductDto> toListCategoryProductDto(List<Product> productList) {
//        return productList.stream()
//                .map(this::toCategoryProduct)
//                .collect(Collectors.toList());
//    }

//}
