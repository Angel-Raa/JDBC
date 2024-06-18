package com.github.angel.service.impl;

import com.github.angel.entity.Product;
import com.github.angel.repository.ProductRepository;
import com.github.angel.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public boolean deleteProductById(Long id) {
       return productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(Product product, Long id) {
        productRepository.update(product, id);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }
}
