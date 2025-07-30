package org.demo.shop1.modules.products.adapters.mappers;

import org.demo.shop1.modules.products.adapters.dto.ProductSaveRequestDTO;
import org.demo.shop1.modules.products.adapters.dto.ProductSaveResponseDTO;
import org.demo.shop1.modules.products.adapters.entities.ProductEntity;
import org.demo.shop1.modules.products.domain.models.Product;

import reactor.core.publisher.Mono;

public class ProductMapper {

    public static ProductEntity toProductEntity(Product productParam) {

        ProductEntity product = new ProductEntity();
        product.setId(productParam.getId());
        product.setSku(productParam.getSku());
        product.setName(productParam.getName());
        product.setCategoryId(productParam.getCategoryId());
        product.setDescription(productParam.getDescription());
        product.setUnitPrice(productParam.getUnitPrice());
        product.setImageUrl(productParam.getImageUrl());
        product.setIsActive(productParam.getIsActive());
        product.setUnitsInStock(productParam.getUnitsInStock());
        product.setDateCreated(productParam.getDateCreated());
        product.setLastUpdated(productParam.getLastUpdated());

        return product;
    }

    public static Product toProduct(ProductEntity productParam) {

        Product product = new Product();
        product.setId(productParam.getId());
        product.setSku(productParam.getSku());
        product.setName(productParam.getName());
        product.setCategoryId(productParam.getCategoryId());
        product.setDescription(productParam.getDescription());
        product.setUnitPrice(productParam.getUnitPrice());
        product.setImageUrl(productParam.getImageUrl());
        product.setIsActive(productParam.getIsActive());
        product.setUnitsInStock(productParam.getUnitsInStock());
        product.setDateCreated(productParam.getDateCreated());
        product.setLastUpdated(productParam.getLastUpdated());

        return product;
    }

    public static Mono<Product> toProductSaveRequest(ProductSaveRequestDTO productRequest) {

        return Mono.just(productRequest).flatMap(p -> {

            Product product = new Product();
            product.setSku(p.getSku());
            product.setName(productRequest.getName());
            product.setCategoryId(productRequest.getCategoryId());
            product.setDescription(productRequest.getDescription());
            product.setUnitPrice(productRequest.getUnitPrice());
            product.setImageUrl(productRequest.getImageUrl());
            product.setUnitsInStock(productRequest.getUnitsInStock());

            return Mono.just(product);
        });
    }

    public static Mono<ProductSaveResponseDTO> toProductSaveResponse(Product productResponse) {

        return Mono.just(productResponse).flatMap(p -> {

            ProductSaveResponseDTO product = new ProductSaveResponseDTO();
            product.setSku(productResponse.getSku());
            product.setName(productResponse.getName());
            product.setCategoryId(productResponse.getCategoryId());
            product.setDescription(productResponse.getDescription());
            product.setUnitPrice(productResponse.getUnitPrice());
            product.setImageUrl(productResponse.getImageUrl());
            product.setUnitsInStock(productResponse.getUnitsInStock());
            product.setIsActive(productResponse.getIsActive());

            return Mono.just(product);
        });
    }
}
