package org.demo.shop1.product.application.query;

import org.demo.shop1.product.domain.model.Product;
import org.demo.shop1.product.domain.repository.ProductQueryRepository;
import org.demo.shop1.product.domain.service.ProductFind;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductFindService implements ProductFind {

    private final ProductQueryRepository productQueryRepository;

    @Override
    public Flux<Product> findAll() {
        return productQueryRepository.findAll();
    }

    @Override
    public Mono<Product> findById(Integer id) {
        return productQueryRepository.findById(id);
    }

    @Override
    public Flux<Product> findByCategoryId(Integer id) {
        return productQueryRepository.findByCategoryId(id);
    }

    @Override
    public Flux<Product> findByNameContaining(String name) {
        return productQueryRepository.findByNameContaining(name);
    }

}
