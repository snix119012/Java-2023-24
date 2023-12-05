package org.simplestore.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<Integer, Product> products = new HashMap<>();
    public synchronized void  addProduct(Product product) {products.put(product.getId(), product);}
    public synchronized Product getProduct(int id) throws ProductNotFoundException {
        Product product = products.get(id);
        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
        return product;
    }
    public synchronized void removeProduct(int id) {products.remove(id);}
    public synchronized Collection<Product> listAllProducts(){return products.values();}
}