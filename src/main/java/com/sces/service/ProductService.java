package com.sces.service;

import com.sces.domain.Product;
import com.sces.repo.ProductRepository;

import java.util.List;

public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo) { this.repo = repo; }

    // US#1: cadastrar produto
    public Product createProduct(String name, String description, int quantity) {
        boolean nameExists = repo.findByName(name).isPresent();
        return repo.saveNew(name, description, quantity, nameExists);
    }

    // US#2: listar produtos
    public List<Product> listProducts() { return repo.findAll(); }

    // US#3: adicionar estoque
    public void addStock(int productId, int amount) {
        var p = repo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        p.addStock(amount);
    }
}
