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
    
}


