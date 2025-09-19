package com.sces.service;

import com.sces.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    @Test
    void shouldCreateProductWithUniqueIdAndValidQuantity() {
        var repo = new ProductRepository();
        var svc = new ProductService(repo);

        var p = svc.createProduct("Teclado", "Mecânico", 10);
        assertTrue(p.getId() > 0);
        assertEquals(10, p.getQuantity());
    }

    @Test
    void shouldRejectDuplicateName() {
        var repo = new ProductRepository();
        var svc = new ProductService(repo);

        svc.createProduct("Mouse", "Óptico", 5);
        assertThrows(IllegalArgumentException.class, () -> svc.createProduct("mouse", "Outro", 1));
    }

    @Test
    void shouldRejectNegativeInitialQuantity() {
        var repo = new ProductRepository();
        var svc = new ProductService(repo);

        assertThrows(IllegalArgumentException.class, () -> svc.createProduct("Monitor", "LED", -1));
    }

    @Test
    void listShouldBeEmptyWhenNoProducts() {
        var svc = new ProductService(new ProductRepository());
        assertTrue(svc.listProducts().isEmpty());
    }

    @Test
    void listShouldReturnInsertedProducts() {
        var repo = new ProductRepository();
        var svc = new ProductService(repo);

        svc.createProduct("Teclado", "Mecânico", 10);
        svc.createProduct("Mouse", "Óptico", 5);

        var list = svc.listProducts();
        assertEquals(2, list.size());
        assertEquals("Teclado", list.get(0).getName());
    }

    @Test
    void shouldAddStockWhenIdExistsAndAmountPositive() {
        var repo = new ProductRepository();
        var svc = new ProductService(repo);

        var p = svc.createProduct("Cabo", "USB-C", 1);
        svc.addStock(p.getId(), 4);

        var after = svc.listProducts().get(0);
        assertEquals(5, after.getQuantity());
    }

    @Test
    void shouldFailWhenProductNotFound() {
        var svc = new ProductService(new ProductRepository());
        assertThrows(IllegalArgumentException.class, () -> svc.addStock(999, 1));
    }

    @Test
    void shouldFailWhenAmountNotPositive() {
        var repo = new ProductRepository();
        var svc = new ProductService(repo);

        var p = svc.createProduct("SSD", "NVMe", 2);
        assertThrows(IllegalArgumentException.class, () -> svc.addStock(p.getId(), 0));
        assertThrows(IllegalArgumentException.class, () -> svc.addStock(p.getId(), -3));
    }

    
}


