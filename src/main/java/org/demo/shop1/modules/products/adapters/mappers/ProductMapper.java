package org.demo.shop1.modules.products.adapters.mappers;

import org.demo.shop1.modules.products.adapters.dto.ProductDeleteRequestDTO;
import org.demo.shop1.modules.products.adapters.dto.ProductDeleteResponseDTO;
import org.demo.shop1.modules.products.adapters.dto.ProductSaveRequestDTO;
import org.demo.shop1.modules.products.adapters.dto.ProductSaveResponseDTO;
import org.demo.shop1.modules.products.adapters.dto.ProductSingleResponse;
import org.demo.shop1.modules.products.adapters.dto.ProductUpdateRequestDTO;
import org.demo.shop1.modules.products.adapters.dto.ProductUpdateResponseDTO;
import org.demo.shop1.modules.products.adapters.entities.ProductEntity;
import org.demo.shop1.modules.products.domain.models.ProductModel;

import reactor.core.publisher.Mono;

public class ProductMapper {

    public static ProductEntity toProductEntity(ProductModel product) {

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

    public static ProductModel toProductModel(ProductEntity product) {

        ProductModel productResult = new ProductModel();
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

    public static Mono<ProductModel> toProductModel(ProductSaveRequestDTO product) {

        return Mono.defer(() -> {

            ProductModel productResult = new ProductModel();
            productResult.setSku(product.getSku());
            productResult.setName(product.getName());
            productResult.setCategoryId(product.getCategoryId());
            productResult.setDescription(product.getDescription());
            productResult.setUnitPrice(product.getUnitPrice());
            productResult.setImageUrl(product.getImageUrl());
            productResult.setUnitsInStock(product.getUnitsInStock());

            return Mono.just(productResult);
        });
    }

    public static Mono<ProductModel> toProductModel(ProductUpdateRequestDTO product) {

        return Mono.defer(() -> {

            ProductModel productResult = new ProductModel();
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

    public static Mono<ProductModel> toProductModel(ProductDeleteRequestDTO product) {

        return Mono.defer(() -> {

            ProductModel productResult = new ProductModel();
            productResult.setSku(product.getSku());

            return Mono.just(productResult);
        });
    }

    public static Mono<ProductSaveResponseDTO> toProductSaveResponse(ProductModel product) {
        return Mono.defer(() -> {
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

    public static Mono<ProductUpdateResponseDTO> toProductUpdateResponse(ProductModel product) {
        return Mono.defer(() -> {
            ProductUpdateResponseDTO productResult = new ProductUpdateResponseDTO();
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

    public static Mono<ProductDeleteResponseDTO> toProductDeleteResponse(ProductModel product) {
        return Mono.defer(() -> {
            ProductDeleteResponseDTO productResult = new ProductDeleteResponseDTO();
            productResult.setSku(product.getSku());
            productResult.setName(product.getName());
            productResult.setCategoryId(product.getCategoryId());

            return Mono.just(productResult);
        });
    }

    public static Mono<ProductSingleResponse> toProductSingleResponse(ProductModel product) {
        return Mono.defer(() -> {
            ProductSingleResponse productResult = new ProductSingleResponse();
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
