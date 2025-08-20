package org.demo.shop1.modules.products.domain.ports.out;

import java.util.List;

import org.demo.shop1.modules.products.domain.models.ProductModel;
import org.springframework.data.mongodb.core.query.Query;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface ProductQueryOutRepository {

    public Mono<ProductModel> findBySku(String sku);

    public Mono<Tuple2<List<ProductModel>, Long>> findAll(Query query);

}
