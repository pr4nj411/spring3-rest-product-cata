package com.example.catalog.repository;

import java.util.List;
import com.example.catalog.model.Product;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(Long id);
    Product findBySku(String sku);
    Product save(Product product);
    Product update(Product product);
    boolean delete(Long id);
}
