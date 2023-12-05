package org.simplestore.service;

import org.junit.jupiter.api.Test;
import org.simplestore.model.Inventory;
import org.simplestore.model.Product;
import org.simplestore.model.ProductNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartConcurrencyTest {
    private final Inventory inventory = new Inventory();

    @Test
    void addAndRemoveItemsConcurrently() throws InterruptedException{
        ShoppingCart shoppingCart = new ShoppingCart(inventory);
        inventory.addProduct(new Product(1, "Test Product", 10.0));
        Thread[] addThreads = new Thread[10];
        for (int i = 0; i < addThreads.length; i++) {
            addThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    shoppingCart.addItem(1, 1);
                }
            });
            addThreads[i].start();
        }
        Thread[] removeThreads = new Thread[5];
        for (int i = 0; i < removeThreads.length; i++) {
            removeThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    shoppingCart.removeItem(1, 1);
                }
            });
            removeThreads[i].start();
        }
        for (Thread thread : addThreads) {
            thread.join();
        }
        for (Thread thread : removeThreads) {
            thread.join();
        }
        assertEquals(50, shoppingCart.getItemQuantity(1));
    }

    @Test
    void calculateTotalCostConcurrently() throws InterruptedException, ProductNotFoundException {
        ShoppingCart shoppingCart = new ShoppingCart(inventory);
        inventory.addProduct(new Product(1, "Test Product", 10.0));
        Thread[] addThreads = new Thread[10];
        for (int i = 0; i < addThreads.length; i++) {
            addThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    shoppingCart.addItem(1, 1);
                }
            });
            addThreads[i].start();
        }
        for (Thread thread : addThreads) {
            thread.join();
        }
        assertEquals(1000.0, shoppingCart.calculateTotalCost());
    }
}