package org.demo.shop1.product.application.command;

import org.demo.shop1.product.domain.model.Product;
import org.demo.shop1.product.domain.repository.ProductCommandRepository;
import org.demo.shop1.product.domain.service.ProductDelete;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductDeleteService implements ProductDelete {

    private final ProductCommandRepository productCommandRepository;

    @Override
    public Mono<Void> delete(Product product) {
        return productCommandRepository.delete(product);
    }

}
