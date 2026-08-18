package com.example.catalog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.catalog.model.Product;
import com.example.catalog.model.ProductRequest;
import com.example.catalog.service.ProductService;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public List<Product> getProducts(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "active", required = false) Boolean active) {
        return service.getProducts(category, active);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable("id") Long id) {
        return service.getProduct(id);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody ProductRequest request) {
        return service.createProduct(request);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Product updateProduct(@PathVariable("id") Long id,
                                 @RequestBody ProductRequest request) {
        return service.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id) {
        service.deleteProduct(id);
    }
}
