package org.demo.shop1.modules.categories.application;

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
    public Mono<CategoryModel> createCategory(CategoryModel category) {
        // Validate unique ID
        // Validate unique NAME
        return categoryQueryApplication.findById(category.getId())
                .doFirst(() -> startMethod("createCategory"))
                .flatMap(existingCategory -> CategoryUtil.error(CategoryMessageEnum.ID_REPEATED))
                .switchIfEmpty(categoryQueryApplication.findByName(category.getName())
                        .flatMap(existingCategory -> CategoryUtil.error(CategoryMessageEnum.NAME_REPEATED))
                        .switchIfEmpty(Mono.defer(() -> {
                            infoMethod("createCategory",
                                    String.format("Creating category with ID: %s and NAME: %s", category.getId(),
                                            category.getName()));

                            // set current active product
                            category.setIsActive(true);

                            return categoryCommandRepository.save(category);
                        })))
                .doOnSuccess(categoryResult -> endMethod("createCategory"));

    }

}
