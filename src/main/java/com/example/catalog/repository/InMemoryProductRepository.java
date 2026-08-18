package com.example.catalog.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.catalog.model.Product;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final Map<Long, Product> products = new LinkedHashMap<Long, Product>();
    private long sequence = 3L;

    public InMemoryProductRepository() {
        products.put(1L, new Product(1L, "LAP-100", "Developer Laptop",
                "16GB RAM, 512GB SSD", new BigDecimal("1299.99"), "COMPUTER", true));
        products.put(2L, new Product(2L, "MON-200", "27 Inch Monitor",
                "4K IPS monitor", new BigDecimal("449.00"), "DISPLAY", true));
        products.put(3L, new Product(3L, "KEY-300", "Mechanical Keyboard",
                "Developer mechanical keyboard", new BigDecimal("129.50"), "ACCESSORY", true));
    }

    @Override
    public synchronized List<Product> findAll() {
        return new ArrayList<Product>(products.values());
    }

    @Override
    public synchronized Product findById(Long id) {
        return products.get(id);
    }

    @Override
    public synchronized Product findBySku(String sku) {
        for (Product product : products.values()) {
            if (product.getSku() != null && product.getSku().equalsIgnoreCase(sku)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public synchronized Product save(Product product) {
        product.setId(sequence++);
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public synchronized Product update(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public synchronized boolean delete(Long id) {
        return products.remove(id) != null;
    }
}
