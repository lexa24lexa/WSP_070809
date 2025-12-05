package com.example.lab07.service;

import com.example.lab07.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    // hashmap to store product (id -> Product)
    private Map<Long, Product> productMap = new HashMap<>();
    private Long nextId = 1L; // creates id

    // c
    public Product addProduct(Product product) {
        product.setId(nextId++);
        productMap.put(product.getId(), product);
        return product;
    }

    // r all
    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    // r id
    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(productMap.get(id));
    }

    // u
    public boolean updateProduct(Long id, Product updatedProduct) {
        if (productMap.containsKey(id)) {
            updatedProduct.setId(id); // same id
            productMap.put(id, updatedProduct);
            return true;
        }
        return false; // not found
    }

    // d
    public boolean deleteProduct(Long id) {
        return productMap.remove(id) != null;
    }
}
