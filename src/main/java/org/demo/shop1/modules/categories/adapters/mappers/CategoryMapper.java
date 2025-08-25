package org.demo.shop1.modules.categories.adapters.mappers;

import org.demo.shop1.modules.categories.adapters.dto.CategoryCreateRequestDTO;
import org.demo.shop1.modules.categories.adapters.dto.CategoryCreateResponseDTO;
import org.demo.shop1.modules.categories.adapters.entities.CategoryEntity;
import org.demo.shop1.modules.categories.domain.models.CategoryModel;

import reactor.core.publisher.Mono;

public class CategoryMapper {

    public static CategoryEntity toCategoryEntity(CategoryModel category) {
        CategoryEntity categoryResult = new CategoryEntity();
        categoryResult.setId(category.getId());
        categoryResult.setName(category.getName());
        categoryResult.setDescription(category.getDescription());
        categoryResult.setIsActive(category.getIsActive());
        categoryResult.setDateCreated(category.getDateCreated());
        categoryResult.setLastUpdated(category.getLastUpdated());

        return categoryResult;
    }

    public static CategoryModel toCategory(CategoryEntity category) {
        CategoryModel categoryResult = new CategoryModel();
        categoryResult.setId(category.getId());
        categoryResult.setName(category.getName());
        categoryResult.setDescription(category.getDescription());
        categoryResult.setIsActive(category.getIsActive());
        categoryResult.setDateCreated(category.getDateCreated());
        categoryResult.setLastUpdated(category.getLastUpdated());

        return categoryResult;
    }

    public static Mono<CategoryModel> toCategory(CategoryCreateRequestDTO category) {

        CategoryModel categoryResult = new CategoryModel();
        categoryResult.setId(category.getId());
        categoryResult.setName(category.getName());
        categoryResult.setDescription(category.getDescription());

        return Mono.just(categoryResult);
    }

    public static Mono<CategoryCreateResponseDTO> toCategorySaveResponse(CategoryModel category) {
        return Mono.defer(() -> {
            CategoryCreateResponseDTO categoryResult = new CategoryCreateResponseDTO();
            categoryResult.setId(category.getId());
            categoryResult.setName(category.getName());
            categoryResult.setDescription(category.getDescription());
            categoryResult.setIsActive(category.getIsActive());

            return Mono.just(categoryResult);
        });
    }
}
