package com.example.lab07.service;

import com.example.lab07.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

  private Map<Long, Product> productMap = new HashMap<>();
  private Long nextId = 1L;

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
  public Optional<Product> updateProduct(Long id, Product updatedProduct) {
    if (productMap.containsKey(id)) {
      updatedProduct.setId(id);
      productMap.put(id, updatedProduct);
      return Optional.of(updatedProduct);
    }
    return Optional.empty();
  }

  // d
  public boolean deleteProduct(Long id) {
    return productMap.remove(id) != null;
  }
}
