package org.demo.shop1.modules.products.application.validation;

import org.demo.shop1.modules.products.domain.enums.ProductIntegerEnum;
import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.exceptions.ProductSaveException;
import org.demo.shop1.modules.products.domain.models.Product;
import org.demo.shop1.modules.products.domain.services.ProductValidationService;
import org.demo.shop1.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductValidationApplication implements ProductValidationService {

    @Override
    public Mono<Product> validateBeforeSave(Product productParam) throws ProductSaveException {
        return Mono.just(productParam).flatMap(product -> {
            // validate SKU
            if (ObjectUtils.isBlankString(product.getSku())) {
                throw new ProductSaveException(ProductMessageEnum.SKU_BLANK.code, ProductMessageEnum.SKU_BLANK.message);
            }
            if (product.getSku().length() < ProductIntegerEnum.SKU_MIN_SIZE.value) {
                throw new ProductSaveException(ProductMessageEnum.SKU_MIN.code, ProductMessageEnum.SKU_MIN.message);
            }

            // validate name
            if (ObjectUtils.isBlankString(product.getName())) {
                throw new ProductSaveException(ProductMessageEnum.NAME_BLANK.code,
                        ProductMessageEnum.NAME_BLANK.message);
            }

            // validate category
            if (product.getCategoryId() == null) {
                throw new ProductSaveException(ProductMessageEnum.CATEGORY_NULL.code,
                        ProductMessageEnum.CATEGORY_NULL.message);
            }

            // validate description
            if (ObjectUtils.isBlankString(product.getDescription())) {
                throw new ProductSaveException(ProductMessageEnum.DESCRIPTION_BLANK.code,
                        ProductMessageEnum.DESCRIPTION_BLANK.message);
            }

            // validate unit price
            if (product.getUnitPrice() == null) {
                throw new ProductSaveException(ProductMessageEnum.UNIT_PRICE_NULL.code,
                        ProductMessageEnum.UNIT_PRICE_NULL.message);
            }
            if (product.getUnitPrice() < ProductIntegerEnum.UNIT_PRICE_MIN.value) {
                throw new ProductSaveException(ProductMessageEnum.UNIT_PRICE_MIN.code,
                        ProductMessageEnum.UNIT_PRICE_MIN.message);
            }

            // validate units in stock
            if (product.getUnitsInStock() == null) {
                throw new ProductSaveException(ProductMessageEnum.UNIT_IN_STOCK_NULL.code,
                        ProductMessageEnum.UNIT_IN_STOCK_NULL.message);
            }
            if (product.getUnitsInStock() < ProductIntegerEnum.UNIT_IN_STOCK_MIN.value) {
                throw new ProductSaveException(ProductMessageEnum.UNIT_IN_STOCK_MIN.code,
                        ProductMessageEnum.UNIT_IN_STOCK_MIN.message);
            }

            return Mono.just(product);
        });
    }

}
