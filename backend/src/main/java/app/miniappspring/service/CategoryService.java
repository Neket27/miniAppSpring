package app.miniappspring.service;

import app.miniappspring.dto.product.category.NumberOfProductsInThisCategory;

import java.util.List;

public interface CategoryService {
    String createCategoryProduct(String categoryName);
    NumberOfProductsInThisCategory getCategories();

    List<String> getAllCategories();
}
