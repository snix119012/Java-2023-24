package org.simplestore.service;

import org.simplestore.model.Inventory;
import org.simplestore.model.Product;
import org.simplestore.model.ProductNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Inventory inventory;
    private final Map<Integer, Integer> cartItems = new HashMap<>();

    public ShoppingCart(Inventory inventory) {
        this.inventory = inventory;
    }
    public synchronized int getItemQuantity(int productId){return cartItems.getOrDefault(productId,0);}

    public synchronized void addItem(int productId, int quantity){
        cartItems.merge(productId, quantity, Integer::sum);  // Equivalent of lambda (a, b) -> Integer.sum(a, b)
    }
    public synchronized void removeItem(int procuctId, int quanity){
        cartItems.merge(procuctId, -quanity,Integer::sum);
        if(cartItems.containsKey(procuctId) && cartItems.get(procuctId)==0){
            cartItems.remove(procuctId);
        }
    }
    public synchronized double calculateTotalCost() throws ProductNotFoundException {
        double cost = 0;
        for(Map.Entry<Integer,Integer> entry : cartItems.entrySet()){
            int id = entry.getKey();
            int quanity = entry.getValue();
            Product prod = inventory.getProduct(id);
            if(prod != null){
                cost = cost + prod.getPrice() *quanity;
            }
        }
        return cost;
    }
    public synchronized void clearCart() {
        cartItems.clear();
    }
}