package org.demo.shop1.modules.categories.application;

import org.demo.shop1.modules.categories.domain.enums.CategoryMessageEnum;
import org.demo.shop1.modules.categories.domain.models.Category;
import org.demo.shop1.modules.categories.domain.ports.out.CategoryCommandOutRepository;
import org.demo.shop1.modules.categories.domain.services.CategoryCommanService;
import org.demo.shop1.modules.categories.domain.services.CategoryQueryService;
import org.demo.shop1.modules.categories.domain.utils.CategoryUtil;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CategoryCommandApplication extends CategoryApplication implements CategoryCommanService {

    private final CategoryCommandOutRepository categoryCommandRepository;
    private final CategoryQueryService categoryQueryService;

    @PostConstruct
    public void init() {
        nameClass = "CategoryCommandApplication";
    }

    @Override
    public Mono<Category> createCategory(Category category) {
        return categoryQueryService.findById(category.getId())
                .doFirst(() -> startMethod("createCategory"))
                .flatMap(existingCategory -> {
                    infoMethod("createCategory",
                            String.format("Already exist a category with ID: ", existingCategory.getId()));
                    return CategoryUtil.error(CategoryMessageEnum.ID_REPEATED);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    infoMethod("createCategory", String.format("Creating category with name: %s", category.getName()));
                    return categoryCommandRepository.save(category);
                }))
                .doOnSuccess(categoryResult -> endMethod("createCategory"));
    }

}
