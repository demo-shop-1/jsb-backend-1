package org.demo.shop1.modules.products.adapters.db;

import org.demo.shop1.modules.products.adapters.entities.ProductEntity;
import org.demo.shop1.modules.products.adapters.mappers.ProductMapper;
import org.demo.shop1.modules.products.domain.enums.ProductFieldEnum;
import org.demo.shop1.modules.products.domain.models.ProductModel;
import org.demo.shop1.modules.products.domain.ports.out.ProductCommandOutRepository;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductCommandMongoRepository implements ProductCommandOutRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<ProductModel> save(ProductModel product) {
        return mongoTemplate.insert(ProductMapper.toProductEntity(product), "products")
                .flatMap(existingProduct -> Mono.just(ProductMapper.toProductModel(existingProduct)));
    }

    @Override
    public Mono<ProductModel> update(ProductModel product) {
        return mongoTemplate.update(ProductEntity.class)
                .matching(new Query(Criteria.where(ProductFieldEnum.SKU.value).is(product.getSku())))
                .apply(new Update()
                        .set(ProductFieldEnum.NAME.value, product.getName())
                        .set(ProductFieldEnum.CATEGORY_ID.value, product.getCategoryId())
                        .set(ProductFieldEnum.DESCRIPTION.value, product.getDescription())
                        .set(ProductFieldEnum.UNIT_PRICE.value, product.getUnitPrice())
                        .set(ProductFieldEnum.IMAGE_URL.value, product.getImageUrl())
                        .set(ProductFieldEnum.IS_ACTIVE.value, product.getIsActive())
                        .set(ProductFieldEnum.UNITS_IN_STOCK.value, product.getUnitsInStock())
                        .set(ProductFieldEnum.LAST_UPDATED.value, product.getLastUpdated()))
                .withOptions(FindAndModifyOptions.options().returnNew(true))
                .findAndModify()
                .flatMap(existingProduct -> Mono.just(ProductMapper.toProductModel(existingProduct)));
    }

}
