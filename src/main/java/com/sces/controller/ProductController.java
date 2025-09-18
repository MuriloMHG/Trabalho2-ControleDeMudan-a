package com.sces.controller;

import com.sces.domain.Product;
import com.sces.repo.ProductRepository;
import com.sces.service.ProductService;

public class ProductController {
    private final ProductService service;

    public ProductController() {
        this.service = new ProductService(new ProductRepository());
    }

    // Caso de uso: cadastrar produto
    public Product register(String name, String description, int quantity) {
        return service.createProduct(name, description, quantity);
    }
}

