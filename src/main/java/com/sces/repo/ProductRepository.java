package com.sces.repo;

import com.sces.domain.Product;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductRepository {
    private final Map<Integer, Product> data = new HashMap<>();
    private final AtomicInteger seq = new AtomicInteger(0);

    public Product saveNew(String name, String description, int quantity, boolean nameExists) {
        if (nameExists) throw new IllegalArgumentException("Nome duplicado");
        int id = seq.incrementAndGet();
        Product p = new Product(id, name, description, quantity);
        data.put(id, p);
        return p;
    }

    public Optional<Product> findById(int id) { return Optional.ofNullable(data.get(id)); }

    public Optional<Product> findByName(String name) {
        return data.values().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<Product> findAll() { return List.copyOf(data.values()); }
}
