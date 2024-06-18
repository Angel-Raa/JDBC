package com.github.angel.service;

import com.github.angel.entity.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    void deleteProduct(Product product);

    boolean deleteProductById(Long id);

    void updateProduct(Product product, Long id);

    List<Product> getAllProduct();

    Product getProductById(Long id);
}
