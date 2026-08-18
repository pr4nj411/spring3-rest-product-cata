package com.example.catalog.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catalog.exception.DuplicateSkuException;
import com.example.catalog.exception.InvalidProductException;
import com.example.catalog.exception.ProductNotFoundException;
import com.example.catalog.model.Product;
import com.example.catalog.model.ProductRequest;
import com.example.catalog.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getProducts(String category, Boolean active) {
        List<Product> result = new ArrayList<Product>();
        for (Product product : repository.findAll()) {
            if (category != null && !category.equalsIgnoreCase(product.getCategory())) {
                continue;
            }
            if (active != null && active.booleanValue() != product.isActive()) {
                continue;
            }
            result.add(product);
        }
        return result;
    }

    @Override
    public Product getProduct(Long id) {
        Product product = repository.findById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }

    @Override
    public Product createProduct(ProductRequest request) {
        validate(request);
        if (repository.findBySku(request.getSku()) != null) {
            throw new DuplicateSkuException(request.getSku());
        }

        Product product = new Product();
        copy(request, product, true);
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductRequest request) {
        Product existing = getProduct(id);
        validate(request);

        Product sameSku = repository.findBySku(request.getSku());
        if (sameSku != null && !sameSku.getId().equals(id)) {
            throw new DuplicateSkuException(request.getSku());
        }

        copy(request, existing, false);
        return repository.update(existing);
    }

    @Override
    public void deleteProduct(Long id) {
        getProduct(id);
        repository.delete(id);
    }

    private void validate(ProductRequest request) {
        if (request == null) {
            throw new InvalidProductException("Request body is required.");
        }
        if (isBlank(request.getSku())) {
            throw new InvalidProductException("sku is required.");
        }
        if (isBlank(request.getName())) {
            throw new InvalidProductException("name is required.");
        }
        if (request.getPrice() == null || request.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidProductException("price must be zero or greater.");
        }
        if (isBlank(request.getCategory())) {
            throw new InvalidProductException("category is required.");
        }
    }

    private void copy(ProductRequest request, Product product, boolean create) {
        product.setSku(request.getSku().trim());
        product.setName(request.getName().trim());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory().trim().toUpperCase());
        product.setActive(request.getActive() == null ? true : request.getActive().booleanValue());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().length() == 0;
    }
}
