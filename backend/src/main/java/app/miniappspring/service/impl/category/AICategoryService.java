package app.miniappspring.service.impl.category;

import app.miniappspring.dto.product.category.NumberOfProductsInThisCategory;
import app.miniappspring.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class AICategoryService {

    private final CategoryService categoryService;

//    @Bean
//    @Description("Getting all categories in the store")
//    public Supplier<List<String>> getAllCategories() {
//        return categoryService::getAllCategories;
//    }

    @Bean
    @Description("Функция для получения всех категорий продуктов магазина.")
    public Supplier<List<String>> getAllCategories() {
        return categoryService::getAllCategories;
    }

    @Bean
    @Description("Retrieves a list of categories along with the count of items in each category.\n" +
            "Each entry in the list contains the category name and the corresponding number of items.")
    public Supplier<NumberOfProductsInThisCategory> getCategoriesAndNumberOfProductsInCategory() {
        return categoryService::getCategoriesWithCountProducts;
    }

    @Bean
    @Description("Create a product category. The function accepts the category name as input, and creates and returns.")
    public Function<CreateCategoryRequest,String> createCategoryProduct(){
        return (createCategoryRequest)->categoryService.createCategoryProduct(createCategoryRequest.categoryName());
    }

}

record CreateCategoryRequest(String categoryName) {}