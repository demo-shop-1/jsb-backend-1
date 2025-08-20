package org.demo.shop1.modules.categories.adapters.db;

import org.demo.shop1.modules.categories.domain.enums.CategoryMessageEnum;
import org.demo.shop1.modules.categories.domain.exceptions.CategoryValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.ReactiveBeforeConvertCallback;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import jakarta.validation.Validator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CategoryValidationMongo<CategoryEntity> implements ReactiveBeforeConvertCallback<CategoryEntity> {

    @Autowired
    private Validator validator;

    @Override
    public @NonNull Mono<CategoryEntity> onBeforeConvert(@NonNull CategoryEntity entity, @NonNull String collection) {
        return Mono.just(validator.validate(entity))
                .flatMapMany(Flux::fromIterable)
                .flatMap(error -> {
                    return Mono.error(new CategoryValidationException(CategoryMessageEnum.CATEGORY_ERROR.code,
                            error.getMessage()));
                }).then(Mono.just(entity));
    }

}
