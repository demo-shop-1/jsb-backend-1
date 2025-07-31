package org.demo.shop1.modules.products.adapters.db;

import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.exceptions.ProductSaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.ReactiveBeforeConvertCallback;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import jakarta.validation.Validator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductValidationMongo<ProductEntity> implements ReactiveBeforeConvertCallback<ProductEntity> {

    @Autowired
    private Validator validator;

    @Override
    public @NonNull Mono<ProductEntity> onBeforeConvert(@NonNull ProductEntity entity, @NonNull String collection) {
        return Mono.just(validator.validate(entity)).flatMapMany(Flux::fromIterable).flatMap(error -> {
            // Throw an error for each validator's error
            return Mono.error(new ProductSaveException(ProductMessageEnum.PRODUCT_ERROR.code, error.getMessage()));
        }).then(Mono.just(entity));
    }

}
