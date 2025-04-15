package app.miniappspring.service.impl.category;

import app.miniappspring.dto.product.category.NumberOfProductsInThisCategory;
import app.miniappspring.entity.Category;
import app.miniappspring.repository.CategoryRepo;
import app.miniappspring.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryItemRepo;

    @Override
    @Transactional
    public List<String> getAllCategories() {
        return categoryItemRepo.findAll().stream().map(categoryItem -> categoryItem.getName()).toList();
    }

    @Override
    @Transactional
    public NumberOfProductsInThisCategory getCategoriesWithCountProducts(){
        Map<String,Integer> countProductThisCategory = new HashMap<>();
        List<Category>categoryItemList = categoryItemRepo.findAll();
        categoryItemList.forEach(categoryItem -> countProductThisCategory.put(categoryItem.getName(), categoryItem.getProductList().size()));
        return new NumberOfProductsInThisCategory(countProductThisCategory);
    }

    @Override
    @Transactional
    public String createCategoryProduct(String categoryName) {
        categoryItemRepo.save(new Category(categoryName));
        return categoryName;
    }

}
