package org.demo.shop1.modules.products.application.validation;

import java.util.Optional;

import org.demo.shop1.modules.categories.domain.services.CategoryQueryService;
import org.demo.shop1.modules.products.application.ProductApplication;
import org.demo.shop1.modules.products.domain.Product;
import org.demo.shop1.modules.products.domain.enums.ProductIntegerEnum;
import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.enums.ProductPaginationEnum;
import org.demo.shop1.modules.products.domain.models.ProductModel;
import org.demo.shop1.modules.products.domain.services.ProductValidationService;
import org.demo.shop1.modules.products.domain.utils.ProductUtil;
import org.demo.shop1.utils.ObjectUtil;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductValidationApplication extends ProductApplication implements ProductValidationService {

    private final CategoryQueryService categoryQueryApplication;

    @PostConstruct
    public void init() {
        nameClass = "ProductValidationApplication";
    }

    @Override
    public Mono<Product> validateBeforeSave(ProductModel product) {
        return Mono.defer(() -> {
            Mono<Product> result = Mono.just(product);
            Boolean hasError = false;

            // validate SKU
            if (!hasError && ObjectUtil.isBlankString(product.getSku())) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.SKU_BLANK);
                hasError = true;
            }
            if (!hasError && product.getSku().length() < ProductIntegerEnum.SKU_MIN_SIZE.value) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.SKU_MIN);
                hasError = true;
            }

            // validate name
            if (!hasError && ObjectUtil.isBlankString(product.getName())) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.NAME_BLANK);
                hasError = true;
            }

            // validate category
            if (!hasError && product.getCategoryId() == null) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.CATEGORY_NULL);
                hasError = true;
            }

            // validate description
            if (!hasError && ObjectUtil.isBlankString(product.getDescription())) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.DESCRIPTION_BLANK);
                hasError = true;
            }

            // validate unit price
            if (!hasError && product.getUnitPrice() == null) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.UNIT_PRICE_NULL);
                hasError = true;
            } else if (!hasError && product.getUnitPrice() < ProductIntegerEnum.UNIT_PRICE_MIN.value) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.UNIT_PRICE_MIN);
                hasError = true;
            }

            // validate units in stock
            if (!hasError && product.getUnitsInStock() == null) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.UNIT_IN_STOCK_NULL);
                hasError = true;
            } else if (!hasError && product.getUnitsInStock() < ProductIntegerEnum.UNIT_IN_STOCK_MIN.value) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.UNIT_IN_STOCK_MIN);
                hasError = true;
            }

            return result;
        })
                .doFirst(() -> startMethod("validateBeforeSave"))
                .doFinally(result -> endMethod("validateBeforeSave"));
    }

    @Override
    public Mono<Product> validateIfCategoryExist(ProductModel product) {
        return categoryQueryApplication.findById(product.getCategoryId())
                .flatMap(existingCategory -> {
                    return Mono.just(product);
                })
                .cast(Product.class)
                .switchIfEmpty(Mono.defer(() -> {
                    return ProductUtil.throwValidationError(ProductMessageEnum.CATEGORY_NOT_EXIST);
                }))
                .doFirst(() -> startMethod("validateIfCategoryExist"))
                .doFinally(categoryResult -> endMethod("validateIfCategoryExist"));

    }

    @Override
    public Mono<Product> validateBeforeUpdate(ProductModel product) {
        return Mono.defer(() -> {
            Mono<Product> result = Mono.just(product);
            Boolean hasError = false;

            // validate name
            if (!hasError && ObjectUtil.isBlankString(product.getName())) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.NAME_BLANK);
                hasError = true;
            }

            // validate category
            if (!hasError && product.getCategoryId() == null) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.CATEGORY_NULL);
                hasError = true;
            }

            // validate description
            if (!hasError && ObjectUtil.isBlankString(product.getDescription())) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.DESCRIPTION_BLANK);
                hasError = true;
            }

            // validate unit price
            if (!hasError && product.getUnitPrice() == null) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.UNIT_PRICE_NULL);
                hasError = true;
            } else if (!hasError && product.getUnitPrice() < ProductIntegerEnum.UNIT_PRICE_MIN.value) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.UNIT_PRICE_MIN);
                hasError = true;
            }

            // validate units in stock
            if (!hasError && product.getUnitsInStock() == null) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.UNIT_IN_STOCK_NULL);
                hasError = true;
            } else if (!hasError && product.getUnitsInStock() < ProductIntegerEnum.UNIT_IN_STOCK_MIN.value) {
                result = ProductUtil.throwValidationError(ProductMessageEnum.UNIT_IN_STOCK_MIN);
                hasError = true;
            }

            // validate if it is active
            if (!hasError && product.getIsActive() == null) {
                infoMethod("validateBeforeUpdate", product.getIsActive().toString());
                result = ProductUtil.throwValidationError(ProductMessageEnum.IS_ACTIVE_NULL);
                hasError = true;
            }

            return result;
        })
                .doFirst(() -> startMethod("validateBeforeUpdate"))
                .doFinally(result -> endMethod("validateBeforeUpdate"));
    }

    @Override
    public Mono<Product> validatePagination(Optional<String> page, Optional<String> size) {
        return Mono.defer(() -> {
            Mono<Product> result = Mono.empty();
            Boolean hasError = false;

            if (!hasError && (!page.isPresent() || Integer.parseInt(page.get()) < 0)) {
                result = ProductUtil.throwPaginationError(ProductPaginationEnum.PAGE_INVALID);
                hasError = true;
            }

            if (!hasError && (!size.isPresent() || Integer.parseInt(size.get()) < 0)) {
                result = ProductUtil.throwPaginationError(ProductPaginationEnum.SIZE_INVALID);
                hasError = true;
            }

            return result;
        });
    }
}
