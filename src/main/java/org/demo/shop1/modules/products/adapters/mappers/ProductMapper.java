package org.demo.shop1.modules.products.adapters.mappers;

import org.demo.shop1.modules.products.adapters.dto.ProductSaveRequestDTO;
import org.demo.shop1.modules.products.adapters.dto.ProductSaveResponseDTO;
import org.demo.shop1.modules.products.adapters.entities.ProductEntity;
import org.demo.shop1.modules.products.domain.models.Product;

import reactor.core.publisher.Mono;

public class ProductMapper {

    public static ProductEntity toProductEntity(Product product) {

        ProductEntity productResult = new ProductEntity();
        productResult.setId(product.getId());
        productResult.setSku(product.getSku());
        productResult.setName(product.getName());
        productResult.setCategoryId(product.getCategoryId());
        productResult.setDescription(product.getDescription());
        productResult.setUnitPrice(product.getUnitPrice());
        productResult.setImageUrl(product.getImageUrl());
        productResult.setIsActive(product.getIsActive());
        productResult.setUnitsInStock(product.getUnitsInStock());
        productResult.setDateCreated(product.getDateCreated());
        productResult.setLastUpdated(product.getLastUpdated());

        return productResult;
    }

    public static Product toProduct(ProductEntity product) {

        Product productResult = new Product();
        productResult.setId(product.getId());
        productResult.setSku(product.getSku());
        productResult.setName(product.getName());
        productResult.setCategoryId(product.getCategoryId());
        productResult.setDescription(product.getDescription());
        productResult.setUnitPrice(product.getUnitPrice());
        productResult.setImageUrl(product.getImageUrl());
        productResult.setIsActive(product.getIsActive());
        productResult.setUnitsInStock(product.getUnitsInStock());
        productResult.setDateCreated(product.getDateCreated());
        productResult.setLastUpdated(product.getLastUpdated());

        return productResult;
    }

    public static Mono<Product> toProduct(ProductSaveRequestDTO product) {

        return Mono.just(product).flatMap(p -> {

            Product productResult = new Product();
            productResult.setSku(p.getSku());
            productResult.setName(product.getName());
            productResult.setCategoryId(product.getCategoryId());
            productResult.setDescription(product.getDescription());
            productResult.setUnitPrice(product.getUnitPrice());
            productResult.setImageUrl(product.getImageUrl());
            productResult.setUnitsInStock(product.getUnitsInStock());

            return Mono.just(productResult);
        });
    }

    public static Mono<ProductSaveResponseDTO> toProductSaveResponse(Product product) {

        return Mono.just(product).flatMap(p -> {

            ProductSaveResponseDTO productResult = new ProductSaveResponseDTO();
            productResult.setSku(product.getSku());
            productResult.setName(product.getName());
            productResult.setCategoryId(product.getCategoryId());
            productResult.setDescription(product.getDescription());
            productResult.setUnitPrice(product.getUnitPrice());
            productResult.setImageUrl(product.getImageUrl());
            productResult.setUnitsInStock(product.getUnitsInStock());
            productResult.setIsActive(product.getIsActive());

            return Mono.just(productResult);
        });
    }
}
