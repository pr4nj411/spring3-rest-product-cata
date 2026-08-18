package com.example.catalog.service;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.catalog.exception.DuplicateSkuException;
import com.example.catalog.exception.ProductNotFoundException;
import com.example.catalog.model.Product;
import com.example.catalog.model.ProductRequest;
import com.example.catalog.repository.InMemoryProductRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductServiceTest {
    private ProductService service;

    @Before
    public void setUp() {
        service = new ProductServiceImpl(new InMemoryProductRepository());
    }

    @Test
    public void shouldListProducts() {
        List<Product> products = service.getProducts(null, null);
        assertEquals(3, products.size());
    }

    @Test
    public void shouldCreateProduct() {
        ProductRequest request = request("MOU-400", "Mouse", "49.99");
        Product product = service.createProduct(request);

        assertNotNull(product.getId());
        assertEquals("MOU-400", product.getSku());
    }

    @Test(expected = DuplicateSkuException.class)
    public void shouldRejectDuplicateSku() {
        service.createProduct(request("LAP-100", "Another Laptop", "10.00"));
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldRejectUnknownProduct() {
        service.getProduct(999L);
    }

    private ProductRequest request(String sku, String name, String price) {
        ProductRequest r = new ProductRequest();
        r.setSku(sku);
        r.setName(name);
        r.setPrice(new BigDecimal(price));
        r.setCategory("ACCESSORY");
        r.setActive(Boolean.TRUE);
        return r;
    }
}
