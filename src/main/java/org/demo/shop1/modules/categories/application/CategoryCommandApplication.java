package org.demo.shop1.modules.categories.application;

import java.util.Calendar;

import org.demo.shop1.modules.categories.domain.Category;
import org.demo.shop1.modules.categories.domain.enums.CategoryMessageEnum;
import org.demo.shop1.modules.categories.domain.models.CategoryModel;
import org.demo.shop1.modules.categories.domain.ports.out.CategoryCommandOutRepository;
import org.demo.shop1.modules.categories.domain.services.CategoryCommandService;
import org.demo.shop1.modules.categories.domain.services.CategoryQueryService;
import org.demo.shop1.modules.categories.domain.utils.CategoryUtil;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CategoryCommandApplication extends CategoryApplication implements CategoryCommandService {

    private final CategoryCommandOutRepository categoryCommandRepository;
    private final CategoryQueryService categoryQueryApplication;

    @PostConstruct
    public void init() {
        nameClass = "CategoryCommandApplication";
    }

    @Override
    public Mono<Category> createCategory(CategoryModel category) {
        // Validate unique ID
        return categoryQueryApplication.findById(category.getId())
                .doFirst(() -> startMethod("createCategory"))
                .cast(CategoryModel.class)
                .flatMap(existingCategory -> {
                    infoMethod("createCategory",
                            String.format("Exist this ID:", existingCategory.getId()));
                    return CategoryUtil.throwValidationError(CategoryMessageEnum.ID_REPEATED);
                })
                .switchIfEmpty(
                        // Validate unique NAME
                        categoryQueryApplication.findByName(category.getName())
                                .cast(CategoryModel.class)
                                .flatMap(existingCategory -> {
                                    infoMethod("createCategory",
                                            String.format("Exist this name:", existingCategory.getName()));
                                    return CategoryUtil.throwValidationError(CategoryMessageEnum.NAME_REPEATED);
                                })
                                .switchIfEmpty(Mono.defer(() -> {
                                    infoMethod("createCategory",
                                            String.format("Creating category with ID: %s and NAME: %s",
                                                    category.getId(),
                                                    category.getName()));

                                    // set current active product
                                    category.setIsActive(true);
                                    // set current date
                                    category.setDateCreated(Calendar.getInstance().getTime());

                                    return categoryCommandRepository.save(category).cast(Category.class);
                                })))
                .doOnSuccess(categoryResult -> endMethod("createCategory"));
    }

}
