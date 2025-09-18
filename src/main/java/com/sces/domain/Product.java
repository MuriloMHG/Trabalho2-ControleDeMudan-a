package com.sces.domain;

import java.util.Objects;

public class Product {
    private final int id;
    private final String name;
    private final String description;
    private int quantity;

    public Product(int id, String name, String description, int quantity) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Nome inválido");
        if (quantity < 0) throw new IllegalArgumentException("Quantidade inicial não pode ser negativa");
        this.id = id;
        this.name = name.trim();
        this.description = description == null ? "" : description.trim();
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getQuantity() { return quantity; }

    public void addStock(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Quantidade a adicionar deve ser > 0");
        this.quantity += amount;
    }

    @Override public boolean equals(Object o) { return o instanceof Product p && id == p.id; }
    @Override public int hashCode() { return Objects.hash(id); }
}
