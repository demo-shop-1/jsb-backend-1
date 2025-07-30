package org.demo.shop1.modules.products.domain.ports.in;

import org.demo.shop1.modules.products.domain.models.Product;

public interface ProductRepositoryIn {
    Product save(Product Product);
}
