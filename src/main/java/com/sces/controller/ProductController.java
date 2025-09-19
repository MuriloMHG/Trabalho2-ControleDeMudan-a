package com.sces.controller;

import com.sces.domain.Product;
import com.sces.repo.ProductRepository;
import com.sces.service.ProductService;

public class ProductController {
    private final ProductService service;

    public ProductController() {
        this.service = new ProductService(new ProductRepository());
        
    }


    public Product register(String name, String description, int quantity) {
        return service.createProduct(name, description, quantity);
    }

    public java.util.List<com.sces.domain.Product> listAll() {
        return service.listProducts();
    }

    public void addStock(int productId, int amount) {
        service.addStock(productId, amount);
    }
    
}