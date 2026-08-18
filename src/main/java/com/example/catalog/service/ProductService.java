package com.example.catalog.service;

import java.util.List;
import com.example.catalog.model.Product;
import com.example.catalog.model.ProductRequest;

public interface ProductService {
    List<Product> getProducts(String category, Boolean active);
    Product getProduct(Long id);
    Product createProduct(ProductRequest request);
    Product updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}
