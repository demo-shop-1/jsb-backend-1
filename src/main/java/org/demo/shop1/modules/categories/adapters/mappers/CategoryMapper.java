package org.demo.shop1.modules.categories.adapters.mappers;

import org.demo.shop1.modules.categories.adapters.dto.CategorySaveRequestDTO;
import org.demo.shop1.modules.categories.adapters.entities.CategoryEntity;
import org.demo.shop1.modules.categories.domain.models.Category;

import reactor.core.publisher.Mono;

public class CategoryMapper {

    public static CategoryEntity toCategoryEntity(Category category) {
        CategoryEntity categoryResult = new CategoryEntity();
        categoryResult.setId(category.getId());
        categoryResult.setName(category.getName());

        return categoryResult;
    }

    public static Category toCategory(CategoryEntity category) {
        Category categoryResult = new Category();
        categoryResult.setId(category.getId());
        categoryResult.setName(category.getName());

        return categoryResult;
    }

    public static Mono<Category> toCategory(CategorySaveRequestDTO category) {

        Category categoryResult = new Category();
        categoryResult.setId(category.getId());
        categoryResult.setName(category.getName());

        return Mono.just(categoryResult);
    }
}
