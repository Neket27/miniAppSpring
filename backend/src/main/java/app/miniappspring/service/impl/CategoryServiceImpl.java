package app.miniappspring.service.impl;

import app.miniappspring.dto.product.category.NumberOfProductsInThisCategory;
import app.miniappspring.entity.CategoryItem;
import app.miniappspring.repository.CategoryItemRepo;
import app.miniappspring.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryItemRepo categoryItemRepo;

    @Override
    @Transactional
    public List<String> getAllCategories() {
        return categoryItemRepo.findAll().stream().map(categoryItem -> categoryItem.getName()).toList();
    }

    @Override
    @Transactional
    public NumberOfProductsInThisCategory getCategories(){
//        Map<String,Integer> countProductThisCategory = Arrays.stream(app.miniappspring.entity.Category.values())
//                .collect(Collectors.toMap(category -> category.getRussianValue(),category->categoryRepo.countByCategory(category)));

        List<CategoryItem>categoryItemList = categoryItemRepo.findAll();

        Map<String,Integer> countProductThisCategory =  categoryItemList.stream().map(categoryItem -> {
            int countProductWithCategory = categoryItemRepo.countByName(categoryItem.getName());
            return Map.entry(categoryItem.getName(), countProductWithCategory);
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return new NumberOfProductsInThisCategory(countProductThisCategory);
    }

    @Override
    @Transactional
    public String createCategoryProduct(String categoryName) {
        categoryItemRepo.save(new CategoryItem(categoryName));
        return categoryName;
    }

}
