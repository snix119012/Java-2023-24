package org.simplestore;

import org.simplestore.model.Inventory;
import org.simplestore.model.Product;
import org.simplestore.model.ProductNotFoundException;
import org.simplestore.service.ShoppingCart;
import org.simplestore.util.InventoryLoader;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        InventoryLoader.loadInventory("src/main/resources/inventory", inventory);
        ShoppingCart shoppingCart = new ShoppingCart(inventory);
        shoppingCart.addItem(1,1);
        shoppingCart.addItem(2,3);
        shoppingCart.addItem(3, 10);
        System.out.println("Count of product number 3 in cart before remove: " + shoppingCart.getItemQuantity(3));
        shoppingCart.removeItem(3,9);
        System.out.println("Count of product number 3 in cart after remove: " + shoppingCart.getItemQuantity(3));
        try {
            System.out.println("Total cost: " + String.format("%.2f",shoppingCart.calculateTotalCost()));
        } catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        Product cookies = new Product(11,"Cookies", 4.99);
        System.out.println("Adding new product to inventory: " + cookies.getName());
        inventory.addProduct(cookies);
        System.out.println("Products in the inventory:");
        for (Product product : inventory.listAllProducts()) {
            System.out.println(product);
        }
        shoppingCart.clearCart();
    }
}