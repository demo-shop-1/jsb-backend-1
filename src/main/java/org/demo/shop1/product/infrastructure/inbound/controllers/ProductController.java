package org.demo.shop1.product.infrastructure.inbound.controllers;

import org.demo.shop1.product.domain.model.Product;
import org.demo.shop1.product.domain.service.ProductFind;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductFind productFindService;

    @GetMapping
    public Mono<ResponseEntity<Flux<Product>>> findAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(productFindService.findAll()));
    }

}
