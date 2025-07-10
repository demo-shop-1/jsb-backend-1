package org.demo.shop1.product.application.command;

import java.util.Calendar;

import org.demo.shop1.product.domain.model.Product;
import org.demo.shop1.product.domain.repository.ProductCommandRepository;
import org.demo.shop1.product.domain.service.ProductSave;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductSaveService implements ProductSave {

    private final ProductCommandRepository productCommandRepository;

    @Override
    public Mono<Product> save(Product product) {
        product.setDateCreated(Calendar.getInstance().getTime());
        return productCommandRepository.save(product);
    }

}
